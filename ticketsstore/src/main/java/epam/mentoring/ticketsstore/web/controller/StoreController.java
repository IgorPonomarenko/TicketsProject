package epam.mentoring.ticketsstore.web.controller;

import epam.mentoring.security.AuthorizationException;
import epam.mentoring.ticketsstore.business.service.TicketsStoreService;
import epam.mentoring.ticketsstore.business.service.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Igor_Ponomarenko on 07.04.2017.
 */

@RestController
@RequestMapping(path = "/store")
public class StoreController {

    @Autowired
    TicketsStoreService ticketsStoreService;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    TicketsRepository ticketsRepository;

    //http://localhost:8083/store/new_tickets?latestProcessedTimestamp=2017-05-03T15:33:14.219&pageSize=50
    @RequestMapping(method = RequestMethod.GET, path = "/new_tickets")
    public List<DtoTicket> getLatestSoldTickets(@RequestParam(value = "latestProcessedTimestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime latestProcessedTimestamp, @RequestParam(value = "pageSize") Integer pageSize) {
        return ticketsStoreService.getLatestTickets(latestProcessedTimestamp, pageSize);
    }

    //http://localhost:8083/store/buy
    @RequestMapping(method = RequestMethod.POST, path = "/buy")
    @ResponseStatus(HttpStatus.OK)
    public void buyTicket(@RequestBody String ticketId) {
        ticketsStoreService.buyTicket(Integer.parseInt(ticketId));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/add_event")
    @ResponseStatus(HttpStatus.OK)
    public Integer addEvent(@RequestBody DtoEvent event) {
        return ticketsStoreService.addNewEvent(event);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/get_events")
    public List<Event> getUpcomingEvents() {
        return ticketsStoreService.getUpcomingEvents();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/get_event_tickets")
    public List<Ticket> getEventTickets(@RequestParam(value = "event_id") int eventId) {
        return ticketsStoreService.getEventTickets(eventId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/get_event_by_id")
    public DtoEvent getEventById(@RequestParam(value = "event_id") int eventId) {
        return ticketsStoreService.getEventById(eventId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/populateDB")
    public void populateDB() {
        ticketsStoreService.addNewEvent(new DtoEvent("RHCP", LocalDateTime.of(2017, 06, 25, 19, 0).toInstant(ZoneOffset.UTC).toEpochMilli(), 150, 1500));
        ticketsStoreService.addNewEvent(new DtoEvent("Muse", LocalDateTime.of(2017, 06, 15, 19, 0).toInstant(ZoneOffset.UTC).toEpochMilli(), 150, 1500));

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthorizationException.class)
    public String return401(AuthorizationException ex) {
        return ex.getMessage();
    }


}
