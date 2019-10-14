package team.mediasoft.probation.services.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Interface entity for database.
 */
@Entity
public interface EntityInterface {

    /**
     * Getter variable identifier.
     * @return id
     */
    @Id
    int getId();

}
