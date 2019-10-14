package team.mediasoft.probation.postalserver.actiondeparture;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import team.mediasoft.probation.postalserver.marshaller.parsertxt.SerializableTXT;
import team.mediasoft.probation.postalserver.marshaller.parserxml.SerializableXML;
import team.mediasoft.probation.postalserver.postaldeparture.PostalDeparture;
import team.mediasoft.probation.postalserver.postaldeparture.UniversalPostalDeparture;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Class-object action on departure.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlRootElement(name = "action")
@XmlSeeAlso({PostalDeparture.class})
public class ActionOnDeparture implements
        Cloneable, Serializable, SerializableTXT, SerializableXML {

    /**
     * Variable id.
     */
    @Setter
    @Getter
    private int id;

    /**
     * Variable-object class UniversalPostalDeparture.
     */
    @Setter
    @Getter
    private UniversalPostalDeparture postalDeparture;

    /**
     * Variable action.
     */
    @Setter
    @Getter
    private Action action;

    /**
     * Variable time action.
     */
    @Setter
    @Getter
    private Date timeAction;
    /**
     * Date and time format.
     */
    private static SimpleDateFormat format
            = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);

    /**
     * Default constructor class.
     */
    public ActionOnDeparture() {
        this.setTimeAction(new Date());
    }

    /**
     * Constructor class.
     * @param receivedPostalDeparture - object class PostalDeparture
     * @param receivedAction - description action
     */
    public ActionOnDeparture(
            final PostalDeparture receivedPostalDeparture,
            final Action receivedAction
    ) {

            this.setTimeAction(new Date());
        this.setAction(receivedAction);
        this.setAction(action);
        this.setPostalDeparture((UniversalPostalDeparture)
                receivedPostalDeparture);
    }

    /**
     * Create clone object class ActionOnDeparture.
     * @return clone object
     * @throws CloneNotSupportedException exception
     */
    public final ActionOnDeparture clone() throws CloneNotSupportedException {

        ActionOnDeparture clone = (ActionOnDeparture) super.clone();
        if (postalDeparture != null) {
            clone.setPostalDeparture((UniversalPostalDeparture)
                    postalDeparture.clone());
        }

        return clone;

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

        setTimeAction(format.parse(list.get(0).toString()));
        setAction(Action.valueOf(list.get(1).toString()));
        setPostalDeparture((UniversalPostalDeparture) list.get(2));

    }

    /**
     * The method returns identifier class.
     * @return list variables
     */
    @JsonIgnore
    public final Integer getIdentifier() {
        return getId();
    }

    /**
     * The method add variable identifier class.
     * @param identifier - variable identifier class
     */
    public final void setIdentifier(final Integer identifier) {
        setId(identifier);
    }

    /**
     * The method returns class variables
     * that do not require additional serialization.
     * @return list variables
     */
    @JsonIgnore
    public final List<Object> getOrdinaryVariable() {
        List list = new ArrayList();
        list.add(getTimeAction());
        list.add(getAction());
        return list;
    }

    /**
     * The method returns class variables
     * that are object references.
     * @return list variables
     */
    @JsonIgnore
    public final List<Object> getObjectVariable() {
        List list = new ArrayList();
        list.add(getPostalDeparture());
        return list;
    }
}
