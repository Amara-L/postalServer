package team.mediasoft.probation.services.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import team.mediasoft.probation.postalserver.postaldeparture.TypePostalDeparture;
import team.mediasoft.probation.postalserver.postaldeparture.UniversalPostalDeparture;

import javax.persistence.*;
import java.util.List;

/**
 * Class-entity UniversalPostalDeparture.
 * To write an entity to the database, you must have not null
 * TypesEntity and derived from it id (in variable idType).
 * Id available calling the method getId() class TypesEntity.
 * To write to the JSON or XML object, need to initialize
 * the text of the type. Variable type (String)
 * is in parent class UniversalPostalDeparture.
 */
@Entity
@Table(name = "postal_departure")
public class PostalDepartureEntity extends UniversalPostalDeparture
        implements EntityInterface {

    /**
     * Enum type postal departure.
     */
    @Setter
    private int idType;

    /**
     * Enum type postal departure.
     */
    @Setter
    private TypesEntity typeEntity;

    /**
     * HashSet for written log activity departure.
     */
    @Setter
    private List<ActionDepartureEntity> actionsDeparture;

    /**
     * Constructor default.
     */
    public PostalDepartureEntity() {
        super();
    }

    /**
     * Constructor with parameter.
     * @param indexRecipient index recipient
     * @param nameRecipient name recipient
     * @param addsRecipient address recipient
     * @param typePostalDepartment type departure
     */
    public PostalDepartureEntity(
            String indexRecipient,
            String nameRecipient,
            String addsRecipient,
            TypePostalDeparture typePostalDepartment
    ) {
        super(indexRecipient, addsRecipient, nameRecipient,
                (new TypesEntity(typePostalDepartment)).getType());
        typeEntity = new TypesEntity(typePostalDepartment);
        idType = typeEntity.getId();
    }

    /**
     * Getter variable id.
     * @return id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    @Override
    public int getId() {
        return super.getId();
    }

    /**
     * Getter adds recipient.
     * @return adds recipient
     */
    @Column(name = "adds_recipient")
    @JsonProperty("adds")
    @Override
    public String getAdressRecipient() {
        return super.getAdressRecipient();
    }

    /**
     * Getter index recipient.
     * @return index recipient
     */
    @Column(name = "index_recipient")
    @JsonProperty("index")
    @Override
    public String getIndexRecipient() {
        return super.getIndexRecipient();
    }

    /**
     * Getter name recipient.
     * @return name recipient
     */
    @Column(name = "name_recipient")
    @JsonProperty("name")
    @Override
    public String getNameRecipient() {
        return super.getNameRecipient();
    }

    /**
     * Getter variable typesEntity.
     * @return TypesEntity
     */
    @JsonIgnore
    @Transient
    public TypesEntity getTypeEntity() {
        return typeEntity;
    }

    /**
     * Getter id type.
     * @return id
     */
    @JsonIgnore
    @Column(name = "id_type")
    public int getIdType() {
        return idType;
    }

    /**
     * Getter variable type.
     * @return type
     */
    @Transient
    @JsonProperty("type")
    @Override
    public String getType() {
        return super.getType();
    }

    /**
     * Setter variable adressRecipient.
     * @param adressRecipient adressRecipient
     */
    @Override
    public void setAdressRecipient(String adressRecipient) {
        super.setAdressRecipient(adressRecipient);
    }

    /**
     * Setter variable id.
     * @param id id
     */
    @Override
    public void setId(int id) {
        super.setId(id);
    }

    /**
     * Setter variable indexRecipient.
     * @param indexRecipient indexRecipient
     */
    @Override
    public void setIndexRecipient(String indexRecipient) {
        super.setIndexRecipient(indexRecipient);
    }

    /**
     * Setter variable nameRecipient.
     * @param nameRecipient nameRecipient
     */
    @Override
    public void setNameRecipient(String nameRecipient) {
        super.setNameRecipient(nameRecipient);
    }

    /**
     * Getter variable actionsDeparture.
     * @return actionsDeparture
     */
    @JsonProperty("actions")
    @JoinColumn(name = "id_postal_departure")
    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    public List<ActionDepartureEntity> getActionsDeparture() {
        return actionsDeparture;
    }
}
