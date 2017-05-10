package epam.mentoring.integrationservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import epam.mentoring.endpoints.Endpoints;
import epam.mentoring.integrationservice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Igor_Ponomarenko on 03.05.2017.
 */
@Component
public class SyncTicketsTask {

    @Autowired
    SystemStatusRepository systemStatusRepository;

    /**
     * This method synchronizes sold tickets between TicketsStore service and Events service.
     * When new ticket is sold at TicketsStore it's picked up by this method and got sent to Event service.
     * Method saves into local database the timestamp of the last synchronized ticket and it's ticket ID.
     * To avoid any missed tickets booked at the same millisecond, method requests from TicketStore all tickets which were sold
     * at the same time or after with the last processed ticket.
     * This makes the latest processed ticket to be picked up with each method call. To avoid duplicate submission to Events service,
     * method checks if picked up ticket has the same ID as last successfully processed ticket. And skips it.
     *
     * @throws URISyntaxException
     */

    @Scheduled(fixedDelay = 5_000)
    public void syncTickets() throws URISyntaxException {

        final String uriEventsAddNewTicket = Endpoints.EVENTS.getSystemURL() + "add_ticket";
        String uriStoreGetSoldTickets = Endpoints.TICKETS_STORE.getSystemURL() + "new_tickets";

        //Prepare headers with appropriate security role assigned
        String accountString = getAccount(Account.ROLE_INTERNAL_CALL);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("account", accountString);

        //Check current system synchronization status. E.g. what is the timestamp of the last successfully processed message.
        SystemStatus systemStatus = systemStatusRepository.findBySystemName(SystemsEnum.EVENTS_SERVICE.toString());
        if (systemStatus == null) {
            systemStatus = new SystemStatus(SystemsEnum.EVENTS_SERVICE.toString(), 0L, 0L, LocalDateTime.MIN, -1);
        }

        //Get sold tickets from TicketsStore
        uriStoreGetSoldTickets = uriStoreGetSoldTickets + "?latestProcessedTimestamp=" + systemStatus.getLatestSuccessfulMessageTimestamp().toString() + "&pageSize=50";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<DtoTicket> storeRequestEntity = new HttpEntity<>(headers);
        ResponseEntity<DtoTicket[]> storeResponseEntity = restTemplate.exchange(uriStoreGetSoldTickets, HttpMethod.GET, storeRequestEntity, DtoTicket[].class);
        DtoTicket[] tickets = storeResponseEntity.getBody();

        //Sorting. Tickets should be processed in sold out order.
        List<DtoTicket> ticketsList = Arrays.asList(tickets);
        Comparator<DtoTicket> comparatorByPurchaseDate = (DtoTicket o1, DtoTicket o2) -> o1.getPurchaseDateTime().compareTo(o2.getPurchaseDateTime());
        ticketsList.sort(comparatorByPurchaseDate);

        for (DtoTicket ticket : ticketsList) {
            //If ticket was already sent to Events service, skip it.
            if (ticket.getTicketID() == systemStatus.getLastProcessedTicketId()) continue;

            //Send ticket to Events service. Depending on response, update synchronization SystemStatus (last successfully processed ticket timestamp and successful/failed messages count).
            HttpEntity<DtoTicket> eventsRequestEntity = new HttpEntity(ticket, headers);
            ResponseEntity eventsResponce = restTemplate.exchange(uriEventsAddNewTicket, HttpMethod.PUT, eventsRequestEntity, DtoTicket.class);

            if (eventsResponce.getStatusCode() == HttpStatus.CREATED ||
                    eventsResponce.getStatusCode() == HttpStatus.ALREADY_REPORTED) {
                systemStatus.setSuccessfulMessages(systemStatus.getSuccessfulMessages() + 1);
                systemStatus.setLastProcessedTicketId(ticket.getTicketID());
                systemStatus.setLatestSuccessfulMessageTimestamp(LocalDateTime.ofInstant(Instant.ofEpochMilli(ticket.getPurchaseDateTime()), ZoneOffset.UTC));
            } else {
                systemStatus.setFailedMessages(systemStatus.getFailedMessages() + 1);
                break;
            }

            systemStatusRepository.save(systemStatus);
        }

    }

    /**
     * Method creates Account object with specified Security Roles, transforms it into JSON and returns it as String
     *
     * @param roles
     * @return
     */
    private String getAccount(String... roles) {

        String accountString = "";
        Account account = new Account(1, roles);

        ObjectMapper mapper = new ObjectMapper();
        try {
            accountString = mapper.writeValueAsString(account);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return accountString;
    }

}
