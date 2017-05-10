package epam.mentoring.events.business.service.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Igor_Ponomarenko on 03.04.2017.
 */
public interface TicketsRepository extends CrudRepository<Ticket, Integer> {
    Ticket findByValidationCode(String validationCode);

    List<Ticket> findByBuyerID(Integer buyerID);

}
