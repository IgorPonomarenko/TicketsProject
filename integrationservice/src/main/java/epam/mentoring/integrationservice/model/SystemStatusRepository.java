package epam.mentoring.integrationservice.model;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Igor_Ponomarenko on 03.05.2017.
 */
public interface SystemStatusRepository extends CrudRepository<SystemStatus, Integer> {
    SystemStatus findBySystemName(String systemName);
}
