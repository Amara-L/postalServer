package team.mediasoft.probation.services.entity;

import lombok.Getter;
import lombok.Setter;
import team.mediasoft.probation.postalserver.actiondeparture.Action;

import javax.persistence.*;

/**
 * Classs-entity actions.
 */
@Entity
@Table(name = "actions")
public class ActionsEntity implements EntityInterface {

    /**
     * Variable id.
     */
    @Id
    @Setter
    @Getter
    private int id;

    /**
     * Variable action.
     */
    @Setter
    @Getter
    private String action;

    /**
     * Variable text action.
     */
    @Setter
    @Getter
    private String actionText;


    /**
     * Constructor default.
     */
    public ActionsEntity() {

    }

    /**
     * Constructor with parameter Action.
     * @param revAction enum Action
     */
    public ActionsEntity(Action revAction) {
        if (revAction == Action.registrationArrival) {
            action = "registrationArrival";
            id = 3;
        } else if (revAction == Action.receivedByTheAddressee) {
            action = "receivedByTheAddressee";
            id = 2;
        } else if (revAction == Action.registrationDispatch) {
            action = "registrationDispatch";
            id = 4;
        } else if (revAction == Action.registrationNewDeparture) {
            action = "registrationNewDeparture";
            id = 1;

        }
        getTextAction();
    }

    /**
     * Get object enum action by text.
     * @param textAction - name action
     * @return enum action
     */
    @Transient
    public Action getEnumAction(String textAction) {
        if (textAction.equals("registrationArrival")) {
            return Action.registrationArrival;
        } else if (textAction.equals("receivedByTheAddressee")) {
            return Action.receivedByTheAddressee;
        } else if (textAction.equals("registrationDispatch")) {
            return Action.registrationDispatch;
        } else if (textAction.equals("registrationNewDeparture")) {
            return Action.registrationNewDeparture;
        } else {
            return null;
        }
    }

    /**
     * Get object text action.
     * @return tetx action
     */
    @Transient
    public String getTextAction() {

        if (action != null) {
            if (action.equals("registrationArrival")) {
                actionText = "Регистрация прибытия";
            } else if (action.equals("receivedByTheAddressee")) {
                actionText = "Получено адресатом";
            } else if (action.equals("registrationDispatch")) {
                actionText = "Регистрация отправки";
            } else if (action.equals("registrationNewDeparture")) {
                actionText = "Регистрация нового отправления";
            }
        }

        return actionText;
    }

    /**
     * Get object enum current action by id.
     * @param revId - id action
     * @return enum action
     */
    @Transient
    public Action getEnumActionById(int revId) {
        if (revId == 1) {
            return Action.registrationNewDeparture;
        } else if (revId == 2) {
            return Action.receivedByTheAddressee;
        } else if (revId == 3) {
            return Action.registrationArrival;
        } else if (revId == 4) {
            return Action.registrationDispatch;
        } else {
            return null;
        }
    }

}
