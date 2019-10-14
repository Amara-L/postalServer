package team.mediasoft.probation.services.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import team.mediasoft.probation.postalserver.actiondeparture.Action;
import team.mediasoft.probation.postalserver.actiondeparture.ActionOnDeparture;
import team.mediasoft.probation.postalserver.postaldepartment.PostalDepartment;
import team.mediasoft.probation.postalserver.postaldeparture.PostalDeparture;

import javax.persistence.*;
import java.util.Date;

/**
 * Class-entity action on departure.
 * To write an entity to the database, you must have not null
 * ActionsEntity and derived from it id (in variable idAction).
 * Id available calling the method getId() class ActionsEntity.
 * To write to the JSON or XML object, need to initialize
 * the text of the action. Text action available calling
 * the method getTextAction() class ActionsEntity.
 */
@Entity
@Table(name = "actions_departure")
public class ActionDepartureEntity extends ActionOnDeparture
        implements EntityInterface {

    /**
     * Variable action.
     */
    @JsonIgnore
    @Setter
    private int idAction;

    /**
     * Variable action.
     */
    @JsonIgnore
    @Setter
    private ActionsEntity actionEntity;

    /**
     * Variable text action.
     */
    @Setter
    private String actionString;

    /**
     * Variable PostalDepartment.
     */
    @Setter
    private PostalDepartmentEntity postalDepartment;

    /**
     * Constructor default.
     */
    public ActionDepartureEntity() {
        super();
    }

    /**
     * Constructor with parameter.
     * @param action - enum Action
     * @param department - object department
     * @param departure - object departure
     */
    public ActionDepartureEntity(
            Action action,
            PostalDepartment department,
            PostalDeparture departure) {
        super(departure, action);
        postalDepartment = (PostalDepartmentEntity) department;
        actionEntity = new ActionsEntity(action);
        idAction = actionEntity.getId();
    }

    /**
     * Getter variable action.
     * @return action
     */
    @JsonIgnore
    @Transient
    @Override
    public Action getAction() {
        return super.getAction();
    }

    /**
     * Getter variable actionsEntity.
     * @return actionsEntity
     */
    @JsonIgnore
    @Transient
    public ActionsEntity getActionEntity() {
        return actionEntity;
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
     * Getter variable timeAction.
     * @return timeAction
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_time")
    @JsonProperty("datetime")
    @Override
    public Date getTimeAction() {
        return super.getTimeAction();
    }

    /**
     * Getter variable postalDepartment.
     * @return postalDepartment
     */

    @JoinColumn(name = "id_postal_department")
    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JsonProperty("place")
    public PostalDepartmentEntity getPostalDepartment() {
        return postalDepartment;
    }

    /**
     * Getter variable postalDeparture.
     * @return postalDeparture
     */
    @JsonIgnore
    @JoinColumn(name = "id_postal_departure")
    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @Override
    public PostalDepartureEntity getPostalDeparture() {
        return (PostalDepartureEntity) super.getPostalDeparture();
    }

    /**
     * Getter id action.
     * @return id
     */
    @Column(name = "id_action")
    @JsonIgnore
    public int getIdAction() {
        return idAction;
    }

    /**
     * Getter text action.
     * @return action string
     */
    @Transient
    @JsonProperty("action")
    public String getActionString() {
        return actionString;
    }
}
