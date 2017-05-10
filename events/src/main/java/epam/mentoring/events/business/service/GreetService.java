package epam.mentoring.events.business.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import epam.mentoring.endpoints.Endpoints;
import epam.mentoring.events.business.service.model.*;
import epam.mentoring.security.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.security.RolesAllowed;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


@Service
public class GreetService {

    TicketsRepository ticketsRepository;
    EventRepository eventRepository;

    protected GreetService() {
    }

    @Autowired
    public GreetService(TicketsRepository ticketsRepository, EventRepository eventRepository) {
        this.ticketsRepository = ticketsRepository;
        this.eventRepository = eventRepository;
    }

    /**
     * Method used to validate ticket by providing validation code
     *
     * @param validationCode
     */
    @RolesAllowed({Account.ROLE_SELLER, Account.ROLE_BUYER})
    public void validateTicket(String validationCode) {
        Ticket ticket = ticketsRepository.findByValidationCode(validationCode);
        if (ticket == null) {
            throw new NoSuchElementException("Validation code not found.");
        } else {
            ticket.setActivated(true);
            ticketsRepository.save(ticket);
        }
    }

    /**
     * Method returns all tickets purchased by a particular user
     *
     * @param customerId
     * @return
     */
    @RolesAllowed({Account.ROLE_SELLER, Account.ROLE_BUYER})
    public List<Ticket> getTicketsByBuyerId(int customerId) {
        List<Ticket> tickets = ticketsRepository.findByBuyerID(customerId);
        if (tickets == null || tickets.size() == 0) {
            throw new NoSuchElementException("No tickets found for this user.");
        } else {
            return tickets;
        }
    }

    /**
     * Method returns validation code by ticket ID
     *
     * @param ticketID
     * @return
     */
    @RolesAllowed({Account.ROLE_SELLER, Account.ROLE_BUYER})
    public String getValidationCodeByTicketID(int ticketID) {
        Ticket ticket = ticketsRepository.findOne(ticketID);
        if (ticket == null) {
            throw new NoSuchElementException("Ticket " + ticketID + "does not exist.");
        } else {
            return ticket.getValidationCode();
        }
    }

    @RolesAllowed({Account.ROLE_INTERNAL_CALL})
    public Ticket addNewTicket(DtoTicket ticket) throws TicketAlreadyExistsException {

        //Check if Event with such Id is present in the DB and if not retrieve it from TicketsStore
        if (null == eventRepository.findOne(ticket.getEventId())) {

            String getEventURL = Endpoints.TICKETS_STORE.getSystemURL() + "get_event_by_id?event_id=" + ticket.getEventId();

            //Prepare headers with appropriate security role assigned
            String accountString = getAccount(Account.ROLE_INTERNAL_CALL);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("account", accountString);

            //Prepare params map
            Map<String, String> params = new HashMap<>();
            params.put("event_id", ticket.getEventId().toString());

            //Send Request to TicketsStore for Entity Details
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<DtoEvent> storeRequestEntity = new HttpEntity<>(headers);
            ResponseEntity<DtoEvent> storeResponseEntity = restTemplate.exchange(getEventURL, HttpMethod.GET, storeRequestEntity, DtoEvent.class, params);
            DtoEvent dtoEvent = storeResponseEntity.getBody();
            eventRepository.save(new Event(dtoEvent));
        }

        if (ticketsRepository.exists(ticket.getTicketID())) {
            throw new TicketAlreadyExistsException("Ticket with Id " + ticket.getTicketID() + " already present in the DB.");
        }
        return ticketsRepository.save(new Ticket(ticket));
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