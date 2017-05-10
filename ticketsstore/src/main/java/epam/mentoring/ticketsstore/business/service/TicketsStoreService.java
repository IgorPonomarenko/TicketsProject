package epam.mentoring.ticketsstore.business.service;

import epam.mentoring.security.Account;
import epam.mentoring.ticketsstore.business.service.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor_Ponomarenko on 07.04.2017.
 */
@Service
public class TicketsStoreService {

    private EventRepository eventRepository;
    private TicketsRepository ticketsRepository;

    @Autowired
    public TicketsStoreService(EventRepository eventRepository, TicketsRepository ticketsRepository) {
        this.eventRepository = eventRepository;
        this.ticketsRepository = ticketsRepository;
    }

    protected TicketsStoreService() {
    }

    @RolesAllowed({Account.ROLE_INTERNAL_CALL})
    public List<DtoTicket> getLatestTickets(LocalDateTime latestProcessedTimestamp, Integer pageSize) {

        List<Ticket> tickets = ticketsRepository.findByPurchaseDateTimeGreaterThanEqual(latestProcessedTimestamp);
        List<DtoTicket> dtoTickets = new ArrayList<>();

        for (Ticket ticket : tickets) {
            dtoTickets.add(new DtoTicket(ticket));
        }

        return dtoTickets;
    }

    @RolesAllowed({Account.ROLE_BUYER})
    public void buyTicket(Integer ticketId) {
        Ticket ticket = ticketsRepository.findOne(ticketId);
        ticket.setPurchaseDateTime(LocalDateTime.now());
        ticket.setSold(true);
        ticketsRepository.save(ticket);
        //TODO: Set Buyer ID, Set Seller ID.
    }

    @RolesAllowed({Account.ROLE_SELLER})
    public Integer addNewEvent(DtoEvent event) {
        Event newEvent = new Event(event.getDescription(), LocalDateTime.ofInstant(Instant.ofEpochMilli(event.getDateTime()), ZoneOffset.UTC), event.getTicketsPrice());
        newEvent = eventRepository.save(newEvent);
        for (int i = 1; i <= event.getTicketsQantity(); i++) {
            ticketsRepository.save(new Ticket(newEvent.getEventID(), i, null, null, false, null, "validation_code - " + i));
        }
        return newEvent.getEventID();
    }

    public List<Event> getUpcomingEvents() {
        List<Event> events = (List<Event>) eventRepository.findAll();
        //List<Event> events = eventRepository.findByDateTimeGreaterThan(LocalDateTime.now());
        //List<Event> events = eventRepository.findByDateTimeBetween(LocalDateTime.now(),LocalDateTime.MAX);

        return events;
    }

    public List<Ticket> getEventTickets(Integer eventId) {
        return ticketsRepository.findByEventId(eventId);
    }

    @RolesAllowed(Account.ROLE_INTERNAL_CALL)
    public DtoEvent getEventById(int eventId) {
        return new DtoEvent(eventRepository.findOne(eventId));
    }
}
