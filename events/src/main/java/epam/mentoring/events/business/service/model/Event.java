package epam.mentoring.events.business.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Created by Igor_Ponomarenko on 03.04.2017.
 */
@Entity
public class Event implements Serializable {

    @Id
    private Integer eventID;

    @Column
    private String description;

    @Column
    private LocalDateTime dateTime;

    @Column
    private Integer ticketPrice;

    protected Event() {
    }

    public Event(Integer eventID, String description, LocalDateTime dateTime, Integer ticketPrice) {
        this.eventID = eventID;
        this.description = description;
        this.dateTime = dateTime;
        this.ticketPrice = ticketPrice;
    }

    public Event(DtoEvent dtoEvent) {
        this.eventID = dtoEvent.getEventID();
        this.description = dtoEvent.getDescription();
        this.dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtoEvent.getDateTime()), ZoneOffset.UTC);
        this.ticketPrice = dtoEvent.getTicketsPrice();
    }

    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (eventID != null ? !eventID.equals(event.eventID) : event.eventID != null) return false;
        if (description != null ? !description.equals(event.description) : event.description != null) return false;
        if (dateTime != null ? !dateTime.equals(event.dateTime) : event.dateTime != null) return false;
        return ticketPrice != null ? ticketPrice.equals(event.ticketPrice) : event.ticketPrice == null;

    }

    @Override
    public int hashCode() {
        int result = eventID != null ? eventID.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (ticketPrice != null ? ticketPrice.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventID=" + eventID +
                ", description='" + description + '\'' +
                ", dateTime=" + dateTime +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
