package team.mediasoft.probation.postalserver.managers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import team.mediasoft.probation.postalserver.actiondeparture.Action;
import team.mediasoft.probation.postalserver.actiondeparture.ActionOnDeparture;
import team.mediasoft.probation.postalserver.marshaller.parsertxt.SerializableTXT;
import team.mediasoft.probation.postalserver.marshaller.parserxml.SerializableXML;
import team.mediasoft.probation.postalserver.postaldepartment.PostalDepartment;
import team.mediasoft.probation.postalserver.postaldeparture.UniversalPostalDeparture;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class-object actions on departure manager.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "actionManager")
public class ActionsOnDepartureManager
        implements SerializableTXT, SerializableXML {

    /**
     * Variable id manager.
     */
    @Setter
    @Getter
    private int idManager;

    /**
     * Variable name manager.
     */
    @Setter
    @Getter
    private String nameManager;

    /**
     * Variable-object class PostalDepartment.
     */
    @Setter
    @Getter
    private PostalDepartment postalDepartment;

    /**
     * Default constructor class.
     */
    public ActionsOnDepartureManager() {
        this.setNameManager(null);
        this.setPostalDepartment(null);
    }

    /**
     * Constructor class.
     * @param receivedNameManager - id manager
     * @param receivedPostalDepartment - object class PostalDepartment
     */
    public ActionsOnDepartureManager(
            final String receivedNameManager,
            final PostalDepartment receivedPostalDepartment) {

        this.setNameManager(receivedNameManager);
        this.setPostalDepartment(receivedPostalDepartment);
    }

    /**
     * Method registration new departure.
     * @param postalDeparture - object class PostalDeparture
     */
    public final void registrationNewDeparture(
            final UniversalPostalDeparture postalDeparture) {

        postalDepartment.getLogActivityParcels().add(
                new ActionOnDeparture(
                        postalDeparture,
                        Action.registrationNewDeparture)
        );

    }

    /**
     * Method registration arrival departure.
     * @param postalDeparture - object class PostalDeparture
     */
    public final void registrationArrivalDeparture(
            final UniversalPostalDeparture postalDeparture) {

        postalDepartment.getLogActivityParcels().add(
                new ActionOnDeparture(
                        postalDeparture,
                        Action.registrationArrival)
        );

    }

    /**
     * Method registration dispatch departure.
     * @param postalDeparture - object class PostalDeparture
     */
    public final void registrationDispatchDeparture(
            final UniversalPostalDeparture postalDeparture) {

        postalDepartment.getLogActivityParcels().add(
                new ActionOnDeparture(
                        postalDeparture,
                        Action.registrationDispatch)
        );

    }

    /**
     * Method registration received by the addressee departure.
     * @param postalDeparture - object class PostalDeparture
     */
    public final void registrationReceivedByTheAddresseeDeparture(
            final UniversalPostalDeparture postalDeparture) {

        postalDepartment.getLogActivityParcels().add(
                new ActionOnDeparture(
                        postalDeparture,
                        Action.receivedByTheAddressee)
        );

    }

    /**
     * Constructor for creating an object when reading from a txt-file.
     * @param list - the resulting list of variables is in order from the
     * transfer of getOrdinaryVariable() and getObjectVariable()
     * @throws Exception - an exception caused when accessing a non-existent
     * index of the list or the inability to cast an object
     */
    public final void serializationConstructor(
            final List<Object> list)
            throws Exception {

        setPostalDepartment((PostalDepartment) list.get(0));

    }

    /**
     * The method returns identifier class.
     * @return list variables
     */
    @JsonIgnore
    public final Integer getIdentifier() {

        return getIdManager();

    }

    /**
     * The method add variable identifier class.
     * @param identifier - variable identifier class
     */
    public final void setIdentifier(final Integer identifier) {

        setIdManager(identifier);

    }

    /**
     * The method returns class variables
     * that do not require additional serialization.
     * @return list variables
     */
    @JsonIgnore
    public final List<Object> getOrdinaryVariable() {
        return null;
    }

    /**
     * The method returns class variables
     * that are object references.
     * @return list variables
     */
    @JsonIgnore
    public final List<Object> getObjectVariable() {
        List list = new ArrayList();
        list.add(getPostalDepartment());
        return list;
    }
}
