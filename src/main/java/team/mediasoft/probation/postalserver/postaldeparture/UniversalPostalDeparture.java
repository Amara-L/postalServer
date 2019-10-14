package team.mediasoft.probation.postalserver.postaldeparture;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import team.mediasoft.probation.postalserver.marshaller.parsertxt.SerializableTXT;
import team.mediasoft.probation.postalserver.marshaller.parserxml.SerializableXML;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Universal class for postal departures objects.
 */
@XmlRootElement(name = "postalDepartures")
public class UniversalPostalDeparture extends
        PostalDeparture implements SerializableTXT, SerializableXML {

    /**
     * Variable type postal departure.
     */
    @Getter
    @Setter
    private String type;

    /**
     * Default constructor class.
     */
    public UniversalPostalDeparture() {
        super();
    }

    /**
     * Constructor class.
     * @param indexRecipient - index postal departure
     * @param adressRecipient - adress postal departure
     * @param nameRecipient - name postal departure
     * @param receivedType - type postal departure
     */
    public UniversalPostalDeparture(
            final String indexRecipient,
            final String adressRecipient,
            final String nameRecipient,
            final String receivedType) {

        super(indexRecipient, adressRecipient, nameRecipient);
        this.type = receivedType;

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
        setIndexRecipient(list.get(0).toString());
        setAdressRecipient(list.get(1).toString());
        setNameRecipient(list.get(2).toString());
        setType(list.get(3).toString());
    }

    /**
     * The method returns class variables
     * that do not require additional serialization.
     * @return list variables
     */
    @JsonIgnore
    public final List<Object> getOrdinaryVariable() {
        List listVariables = new ArrayList<Object>();
        listVariables.add(getIndexRecipient());
        listVariables.add(getAdressRecipient());
        listVariables.add(getNameRecipient());
        listVariables.add(type);
        return listVariables;
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
     * that are object references.
     * @return list variables
     */
    @JsonIgnore
    public final List<Object> getObjectVariable() {
        return null;
    }

}
