package team.mediasoft.probation.services.entity;

import team.mediasoft.probation.postalserver.managers.ActionsOnDepartureManager;
import team.mediasoft.probation.postalserver.postaldepartment.PostalDepartment;

import javax.persistence.*;

/**
 * Class-entity Departure Manager.
 */
@Entity
@Table(name = "departure_manager")
public class DepartureManagerEntity extends ActionsOnDepartureManager
        implements EntityInterface {

    /**
     * Constructor default.
     */
    public DepartureManagerEntity() {
        super();
    }

    /**
     * Constructor with parameter.
     * @param name name manager
     * @param department object department
     */
    public DepartureManagerEntity(
            String name,
            PostalDepartment department) {
        super(name, department);
    }

    /**
     * Getter variable id manager.
     * @return idManager
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Override
    public int getIdManager() {
        return super.getIdManager();
    }

    /**
     * Getter variable name manager.
     * @return nameManager
     */
    @Column(name = "name")
    @Override
    public String getNameManager() {
        return super.getNameManager();
    }

    /**
     * Getter variable-object class PostalDepartment.
     * @return postalDepartment
     */
    @JoinColumn(name = "id_postal_department")
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @Override
    public PostalDepartmentEntity getPostalDepartment() {
        return (PostalDepartmentEntity) super.getPostalDepartment();
    }

    @Transient
    @Override
    public int getId() {
        return super.getIdManager();
    }
}
