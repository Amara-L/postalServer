package team.mediasoft.probation.services.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import team.mediasoft.probation.postalserver.postaldepartment.PostalDepartment;

import javax.persistence.*;
import java.util.List;

/**
 * Class-entity postal department.
 */
@Entity
@Table(name = "postal_department")
public class PostalDepartmentEntity extends PostalDepartment
        implements EntityInterface {

    /**
     * HashSet for written log activity parcels.
     */
    @Setter
    private List<ActionDepartureEntity> logActivity;

    /**
     * Constructor default.
     */
    public PostalDepartmentEntity() {
        super();
    }

    /**
     * Constructor with parameter.
     * @param index index postal department
     * @param name name postal department
     * @param adds address postal department
     */
    public PostalDepartmentEntity(
            String index,
            String name,
            String adds
    ) {
        super(index, name, adds);
    }

    /**
     * Getter variable id postal department.
     * @return id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    @Override
    public int getId() {
        return super.getId();
    }

    /**
     * Getter variable index postal department.
     * @return indexPostDepartment.
     */
    @JsonProperty("indexOffice")
    @Column(name = "index")
    @Override
    public String getIndexPostDepartment() {
        return super.getIndexPostDepartment();
    }

    /**
     * Getter variable name postal department.
     * @return namePostDepartment.
     */
    @JsonProperty("nameOffice")
    @Column(name = "name")
    @Override
    public String getNamePostDepartment() {
        return super.getNamePostDepartment();
    }

    /**
     * Getter variable adds postal department.
     * @return adressPostDepartment.
     */
    @JsonProperty("addsOffice")
    @Column(name = "adds")
    @Override
    public String getAdressPostDepartment() {
        return super.getAdressPostDepartment();
    }

    /**
     * Getter variable log activity parcels.
     * @return logActivity.
     */
    @JoinColumn(name = "id_postal_department")
    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    public List<ActionDepartureEntity> getLogActivity() {
        return logActivity;
    }
}
