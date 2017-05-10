package epam.mentoring.ticketsstore.business.service.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Igor_Ponomarenko on 03.04.2017.
 */
@RepositoryRestResource(collectionResourceRel = "tickets", path = "tickets")
public interface TicketsRepository extends CrudRepository<Ticket, Integer> {
    List<Ticket> findByPurchaseDateTimeGreaterThanEqual(LocalDateTime purchaseDateTime);

    List<Ticket> findByEventId(Integer eventId);

}
