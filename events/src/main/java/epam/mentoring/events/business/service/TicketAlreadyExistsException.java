package epam.mentoring.events.business.service;

/**
 * Created by Igor_Ponomarenko on 19.04.2017.
 */
public class TicketAlreadyExistsException extends Exception {
    public TicketAlreadyExistsException() {
    }

    public TicketAlreadyExistsException(String message) {
        super(message);
    }

    public TicketAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public TicketAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public TicketAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
