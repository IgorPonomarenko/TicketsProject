package epam.mentoring.integrationservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Created by Igor_Ponomarenko on 03.05.2017.
 */
@Entity
public class SystemStatus {

    @Id
    String systemName;

    @Column
    long successfulMessages;

    @Column
    long failedMessages;

    @Column
    LocalDateTime latestSuccessfulMessageTimestamp;

    @Column
    Integer lastProcessedTicketId;

    public SystemStatus(String systemName, long successfulMessages, long failedMessages, LocalDateTime latestSuccessfulMessageTimestamp, Integer lastProcessedTicketId) {
        this.systemName = systemName;
        this.successfulMessages = successfulMessages;
        this.failedMessages = failedMessages;
        this.latestSuccessfulMessageTimestamp = latestSuccessfulMessageTimestamp;
        this.lastProcessedTicketId = lastProcessedTicketId;
    }

    public SystemStatus() {
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public long getSuccessfulMessages() {
        return successfulMessages;
    }

    public void setSuccessfulMessages(long successfulMessages) {
        this.successfulMessages = successfulMessages;
    }

    public long getFailedMessages() {
        return failedMessages;
    }

    public void setFailedMessages(long failedMessages) {
        this.failedMessages = failedMessages;
    }

    public LocalDateTime getLatestSuccessfulMessageTimestamp() {
        return latestSuccessfulMessageTimestamp;
    }

    public void setLatestSuccessfulMessageTimestamp(LocalDateTime latestSuccessfulMessageTimestamp) {
        this.latestSuccessfulMessageTimestamp = latestSuccessfulMessageTimestamp;
    }

    public Integer getLastProcessedTicketId() {
        return lastProcessedTicketId;
    }

    public void setLastProcessedTicketId(Integer lastProcessedTicketId) {
        this.lastProcessedTicketId = lastProcessedTicketId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SystemStatus that = (SystemStatus) o;

        if (successfulMessages != that.successfulMessages) return false;
        if (failedMessages != that.failedMessages) return false;
        if (systemName != null ? !systemName.equals(that.systemName) : that.systemName != null) return false;
        if (latestSuccessfulMessageTimestamp != null ? !latestSuccessfulMessageTimestamp.equals(that.latestSuccessfulMessageTimestamp) : that.latestSuccessfulMessageTimestamp != null)
            return false;
        return lastProcessedTicketId != null ? lastProcessedTicketId.equals(that.lastProcessedTicketId) : that.lastProcessedTicketId == null;

    }

    @Override
    public int hashCode() {
        int result = systemName != null ? systemName.hashCode() : 0;
        result = 31 * result + (int) (successfulMessages ^ (successfulMessages >>> 32));
        result = 31 * result + (int) (failedMessages ^ (failedMessages >>> 32));
        result = 31 * result + (latestSuccessfulMessageTimestamp != null ? latestSuccessfulMessageTimestamp.hashCode() : 0);
        result = 31 * result + (lastProcessedTicketId != null ? lastProcessedTicketId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SystemStatus{" +
                "systemName='" + systemName + '\'' +
                ", successfulMessages=" + successfulMessages +
                ", failedMessages=" + failedMessages +
                ", latestSuccessfulMessageTimestamp=" + latestSuccessfulMessageTimestamp +
                ", lastProcessedTicketId=" + lastProcessedTicketId +
                '}';
    }
}
