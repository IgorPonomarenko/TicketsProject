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
public class Ticket implements Serializable {

    @Id
    private Integer ticketID;

    @Column
    private Integer sitPlace;

    @Column
    private Integer buyerID;

    @Column
    private Integer sellerID;

    @Column
    private LocalDateTime purchaseDateTime;

    //May be replaced with QR code in the future - the code, which is unique for each ticket and is used as a passcode
    @Column
    private String validationCode;

    @Column
    private boolean isActivated;

    protected Ticket() {
    }

    public Ticket(Integer ticketID, Integer sitPlace, Integer buyerID, Integer sellerID, LocalDateTime purchaseDateTime, String validationCode, boolean isActivated) {
        this.ticketID = ticketID;
        this.sitPlace = sitPlace;
        this.buyerID = buyerID;
        this.sellerID = sellerID;
        this.purchaseDateTime = purchaseDateTime;
        this.validationCode = validationCode;
        this.isActivated = isActivated;
    }

    public Ticket(DtoTicket dtoTicket) {
        this.ticketID = dtoTicket.getTicketID();
        this.buyerID = dtoTicket.getBuyerID();
        this.sellerID = dtoTicket.getSellerID();
        this.purchaseDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtoTicket.getPurchaseDateTime()), ZoneOffset.UTC);
        this.validationCode = dtoTicket.getValidationCode();
        this.isActivated = false;
    }

    public Integer getTicketID() {
        return ticketID;
    }

    public void setTicketID(Integer ticketID) {
        this.ticketID = ticketID;
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

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (isActivated != ticket.isActivated) return false;
        if (ticketID != null ? !ticketID.equals(ticket.ticketID) : ticket.ticketID != null) return false;
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
        result = 31 * result + (sitPlace != null ? sitPlace.hashCode() : 0);
        result = 31 * result + (buyerID != null ? buyerID.hashCode() : 0);
        result = 31 * result + (sellerID != null ? sellerID.hashCode() : 0);
        result = 31 * result + (purchaseDateTime != null ? purchaseDateTime.hashCode() : 0);
        result = 31 * result + (validationCode != null ? validationCode.hashCode() : 0);
        result = 31 * result + (isActivated ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketID=" + ticketID +
                ", sitPlace=" + sitPlace +
                ", buyerID=" + buyerID +
                ", sellerID=" + sellerID +
                ", purchaseDateTime=" + purchaseDateTime +
                ", validationCode='" + validationCode + '\'' +
                ", isActivated=" + isActivated +
                '}';
    }
}
