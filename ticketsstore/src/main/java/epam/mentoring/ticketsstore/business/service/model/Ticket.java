package epam.mentoring.ticketsstore.business.service.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Igor_Ponomarenko on 03.04.2017.
 */
@Entity
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketID;

    @Column
    private Integer eventId;

    @Column
    private Integer sitPlace;

    @Column
    private Integer buyerID;

    @Column
    private Integer sellerID;

    @Column
    private boolean isSold;

    @Column
    private LocalDateTime purchaseDateTime;

    //TODO: May be replaced with QR code in the future - the code, which is unique for each ticket and is used as a passcode
    @Column
    private String validationCode;

    protected Ticket() {
    }

    public Ticket(Integer eventId, Integer sitPlace, Integer buyerID, Integer sellerID, boolean isSold, LocalDateTime purchaseDateTime, String validationCode) {
        this.eventId = eventId;
        this.sitPlace = sitPlace;
        this.buyerID = buyerID;
        this.sellerID = sellerID;
        this.isSold = isSold;
        this.purchaseDateTime = purchaseDateTime;
        this.validationCode = validationCode;
    }

    public Integer getTicketID() {
        return ticketID;
    }

    public void setTicketID(Integer ticketID) {
        this.ticketID = ticketID;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getSitPlace() {
        return sitPlace;
    }

    public void setSitPlace(Integer sitPlace) {
        this.sitPlace = sitPlace;
    }

    public Integer getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(Integer buyerID) {
        this.buyerID = buyerID;
    }

    public Integer getSellerID() {
        return sellerID;
    }

    public void setSellerID(Integer sellerID) {
        this.sellerID = sellerID;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public LocalDateTime getPurchaseDateTime() {
        return purchaseDateTime;
    }

    public void setPurchaseDateTime(LocalDateTime purchaseDateTime) {
        this.purchaseDateTime = purchaseDateTime;
    }

    public String getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (isSold != ticket.isSold) return false;
        if (ticketID != null ? !ticketID.equals(ticket.ticketID) : ticket.ticketID != null) return false;
        if (eventId != null ? !eventId.equals(ticket.eventId) : ticket.eventId != null) return false;
        if (sitPlace != null ? !sitPlace.equals(ticket.sitPlace) : ticket.sitPlace != null) return false;
        if (buyerID != null ? !buyerID.equals(ticket.buyerID) : ticket.buyerID != null) return false;
        if (sellerID != null ? !sellerID.equals(ticket.sellerID) : ticket.sellerID != null) return false;
        if (purchaseDateTime != null ? !purchaseDateTime.equals(ticket.purchaseDateTime) : ticket.purchaseDateTime != null)
            return false;
        return validationCode != null ? validationCode.equals(ticket.validationCode) : ticket.validationCode == null;

    }

    @Override
    public int hashCode() {
        int result = ticketID != null ? ticketID.hashCode() : 0;
        result = 31 * result + (eventId != null ? eventId.hashCode() : 0);
        result = 31 * result + (sitPlace != null ? sitPlace.hashCode() : 0);
        result = 31 * result + (buyerID != null ? buyerID.hashCode() : 0);
        result = 31 * result + (sellerID != null ? sellerID.hashCode() : 0);
        result = 31 * result + (isSold ? 1 : 0);
        result = 31 * result + (purchaseDateTime != null ? purchaseDateTime.hashCode() : 0);
        result = 31 * result + (validationCode != null ? validationCode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketID=" + ticketID +
                ", eventId=" + eventId +
                ", sitPlace=" + sitPlace +
                ", buyerID=" + buyerID +
                ", sellerID=" + sellerID +
                ", isSold=" + isSold +
                ", purchaseDateTime=" + purchaseDateTime +
                ", validationCode='" + validationCode + '\'' +
                '}';
    }
}
