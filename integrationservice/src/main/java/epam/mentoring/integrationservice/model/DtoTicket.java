package epam.mentoring.integrationservice.model;

import java.io.Serializable;

/**
 * Created by Igor_Ponomarenko on 03.04.2017.
 */
public class DtoTicket implements Serializable {

    private Integer ticketID;

    private Integer eventId;

    private Integer sitPlace;

    private Integer buyerID;

    private Integer sellerID;

    private boolean isSold;

    private Long purchaseDateTime;

    private String validationCode;

    protected DtoTicket() {
    }

    public DtoTicket(Integer ticketID, Integer eventId, Integer sitPlace, Integer buyerID, Integer sellerID, boolean isSold, Long purchaseDateTime, String validationCode) {
        this.ticketID = ticketID;
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

    public Long getPurchaseDateTime() {
        return purchaseDateTime;
    }

    public void setPurchaseDateTime(Long purchaseDateTime) {
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

        DtoTicket dtoTicket = (DtoTicket) o;

        if (isSold != dtoTicket.isSold) return false;
        if (ticketID != null ? !ticketID.equals(dtoTicket.ticketID) : dtoTicket.ticketID != null) return false;
        if (eventId != null ? !eventId.equals(dtoTicket.eventId) : dtoTicket.eventId != null) return false;
        if (sitPlace != null ? !sitPlace.equals(dtoTicket.sitPlace) : dtoTicket.sitPlace != null) return false;
        if (buyerID != null ? !buyerID.equals(dtoTicket.buyerID) : dtoTicket.buyerID != null) return false;
        if (sellerID != null ? !sellerID.equals(dtoTicket.sellerID) : dtoTicket.sellerID != null) return false;
        if (purchaseDateTime != null ? !purchaseDateTime.equals(dtoTicket.purchaseDateTime) : dtoTicket.purchaseDateTime != null)
            return false;
        return validationCode != null ? validationCode.equals(dtoTicket.validationCode) : dtoTicket.validationCode == null;

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
        return "DtoTicket{" +
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
