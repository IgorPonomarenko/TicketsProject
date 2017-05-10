package epam.mentoring.ticketsstore.business.service.model;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Igor_Ponomarenko on 03.04.2017.
 */

public interface EventRepository extends CrudRepository<Event, Integer> {

    List<Event> findByDateTimeGreaterThan(LocalDateTime dateTime);

    List<Event> findByDateTimeBetween(LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo);

}
