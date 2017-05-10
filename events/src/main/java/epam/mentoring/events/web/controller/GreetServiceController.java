package epam.mentoring.events.web.controller;

import epam.mentoring.events.business.service.GreetService;
import epam.mentoring.events.business.service.TicketAlreadyExistsException;
import epam.mentoring.events.business.service.model.DtoTicket;
import epam.mentoring.events.business.service.model.Ticket;
import epam.mentoring.security.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Igor_Ponomarenko on 03.04.2017.
 */
@RestController
@RequestMapping(path = "/greetservice")
public class GreetServiceController {

    GreetService greetService;

    @Autowired
    public GreetServiceController(GreetService greetService) {
        this.greetService = greetService;
    }

    protected GreetServiceController() {
    }

    //http://localhost:8082/greetservice/activate
    @RequestMapping(method = RequestMethod.POST, path = "/activate")
    @ResponseStatus(HttpStatus.OK)
    public void validateTicket(@RequestBody String validationCode) {
        greetService.validateTicket(validationCode);
    }

    //http://localhost:8082/greetservice/tickets?customer_id=12
    @RequestMapping(method = RequestMethod.GET, path = "/tickets")
    public List<Ticket> getTicketsByBuyerId(@RequestParam(value = "customer_id") int customerId) {
        return greetService.getTicketsByBuyerId(customerId);
    }

    //TODO: Return QR code instead of Validation Code
    //http://localhost:8082/greetservice/validation?ticket_id=125
    @RequestMapping(method = RequestMethod.GET, path = "/validation")
    public String getValidationCodeByTicketID(@RequestParam(value = "ticket_id") int ticketID) {
        return greetService.getValidationCodeByTicketID(ticketID);
    }

    //http://localhost:8082/greetservice/add_ticket
    @RequestMapping(method = RequestMethod.PUT, path = "/add_ticket")
    public ResponseEntity addNewTicket(@RequestBody DtoTicket ticket) {

        try {
            Ticket savedTicket = greetService.addNewTicket(ticket);
            if (savedTicket != null) {
                return ResponseEntity.status(HttpStatus.CREATED).build();
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (TicketAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build();
        }
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
