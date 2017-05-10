package epam.mentoring.ticketsstore.business.service.model;

import java.time.ZoneOffset;

/**
 * Created by Igor_Ponomarenko on 19.04.2017.
 */
public class DtoEvent {

    private Integer eventID;

    private String description;

    private Long dateTime;

    private Integer ticketsQantity;

    private Integer ticketsPrice;

    public DtoEvent(String description, Long dateTime, Integer ticketsQantity, Integer ticketsPrice) {
        this.description = description;
        this.dateTime = dateTime;
        this.ticketsQantity = ticketsQantity;
        this.ticketsPrice = ticketsPrice;
    }

    public DtoEvent(Event event) {
        this.eventID = event.getEventID();
        this.description = event.getDescription();
        this.dateTime = event.getDateTime().toInstant(ZoneOffset.UTC).toEpochMilli();
        this.ticketsQantity = 0;
        this.ticketsPrice = event.getTicketPrice();
    }

    public DtoEvent(Integer eventID, String description, Long dateTime, Integer ticketsQantity, Integer ticketsPrice) {
        this.eventID = eventID;
        this.description = description;
        this.dateTime = dateTime;
        this.ticketsQantity = ticketsQantity;
        this.ticketsPrice = ticketsPrice;
    }

    public DtoEvent() {
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

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getTicketsQantity() {
        return ticketsQantity;
    }

    public void setTicketsQantity(Integer ticketsQantity) {
        this.ticketsQantity = ticketsQantity;
    }

    public Integer getTicketsPrice() {
        return ticketsPrice;
    }

    public void setTicketsPrice(Integer ticketsPrice) {
        this.ticketsPrice = ticketsPrice;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DtoEvent dtoEvent = (DtoEvent) o;

        if (eventID != null ? !eventID.equals(dtoEvent.eventID) : dtoEvent.eventID != null) return false;
        if (description != null ? !description.equals(dtoEvent.description) : dtoEvent.description != null)
            return false;
        if (dateTime != null ? !dateTime.equals(dtoEvent.dateTime) : dtoEvent.dateTime != null) return false;
        if (ticketsQantity != null ? !ticketsQantity.equals(dtoEvent.ticketsQantity) : dtoEvent.ticketsQantity != null)
            return false;
        return ticketsPrice != null ? ticketsPrice.equals(dtoEvent.ticketsPrice) : dtoEvent.ticketsPrice == null;
    }

    @Override
    public int hashCode() {
        int result = eventID != null ? eventID.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (ticketsQantity != null ? ticketsQantity.hashCode() : 0);
        result = 31 * result + (ticketsPrice != null ? ticketsPrice.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DtoEvent{" +
                "eventID=" + eventID +
                ", description='" + description + '\'' +
                ", dateTime=" + dateTime +
                ", ticketsQantity=" + ticketsQantity +
                ", ticketsPrice=" + ticketsPrice +
                '}';
    }
}
