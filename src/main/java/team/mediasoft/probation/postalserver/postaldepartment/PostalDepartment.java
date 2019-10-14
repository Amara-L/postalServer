package team.mediasoft.probation.postalserver.postaldepartment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import team.mediasoft.probation.postalserver.actiondeparture.ActionOnDeparture;
import team.mediasoft.probation.postalserver.marshaller.parsertxt.SerializableTXT;
import team.mediasoft.probation.postalserver.marshaller.parserxml.SerializableXML;
import team.mediasoft.probation.postalserver.postaldeparture.PostalDeparture;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Class-object postal department.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "postalDepartment")
@XmlSeeAlso({ActionOnDeparture.class, PostalDeparture.class})
public class PostalDepartment implements
        Cloneable, Serializable, SerializableTXT, SerializableXML {

    /**
     * Variable id postal department.
     */
    @Setter
    @Getter
    private int id;

    /**
     * Variable index postal department.
     */
    @Setter
    @Getter
    private String indexPostDepartment;

    /**
     * Variable name postal department.
     */
    @Setter
    @Getter
    private String namePostDepartment;

    /**
     * Variable adress postal department.
     */
    @Setter
    @Getter
    private String adressPostDepartment;

    /**
     * HashSet for written log activity parcels.
     */
    @Setter
    @Getter
    @XmlElementWrapper(name = "logActivityParcels", nillable = true)
    @XmlElement(name = "actionOnDeparture")
    private List<ActionOnDeparture> logActivityParcels;

    /**
     * Default constructor class.
     */
    public PostalDepartment() {

        this.setIndexPostDepartment(null);
        this.setNamePostDepartment(null);
        this.setAdressPostDepartment(null);

        logActivityParcels = new ArrayList<ActionOnDeparture>();

    }

    /**
     * Constructor class.
     * @param receivedIndexPostDepartment - index postal department
     * @param receivedNamePostDepartment - name postal department
     * @param receivedAdressPostDepartment - adress postal department
     */
    public PostalDepartment(
            final String receivedIndexPostDepartment,
            final String receivedNamePostDepartment,
            final String receivedAdressPostDepartment) {

        this.setIndexPostDepartment(receivedIndexPostDepartment);
        this.setNamePostDepartment(receivedNamePostDepartment);
        this.setAdressPostDepartment(receivedAdressPostDepartment);

        logActivityParcels = new ArrayList<ActionOnDeparture>();

    }

    /**
     * Method selection log activity parcel by string.
     * @param searchQuery - string search query
     * @return collection with found postal departures
     */
    public final ArrayList<PostalDeparture>
    selectionLogActivityParcelByStringViaRegularExpressions(
            final String searchQuery) {

        ArrayList<PostalDeparture> foundPostalDepartures
                = new ArrayList<PostalDeparture>();

        if (!searchQuery.isEmpty()) {

            String searchQueryString = searchQuery.toLowerCase();
            Pattern p = Pattern.compile(".*" + searchQueryString + ".*");

            for (ActionOnDeparture actionOnDeparture : logActivityParcels) {

                if (
                        (p.matcher(actionOnDeparture.getPostalDeparture()
                                .getNameRecipient().toLowerCase()))
                                .matches()
                                || (p.matcher(actionOnDeparture
                                .getPostalDeparture()
                                .getAdressRecipient().toLowerCase()))
                                .matches()) {

                    if (!foundPostalDepartures.contains(
                            actionOnDeparture.getPostalDeparture())) {
                        foundPostalDepartures.add(actionOnDeparture
                                .getPostalDeparture());
                    }

                }

            }

        }

        foundPostalDepartures
                .sort(Comparator.comparing(PostalDeparture::getNameRecipient));

        return foundPostalDepartures;

    }

    /**
     * Method selection log activity parcel by string.
     * @param searchQuery - string search query
     * @return collection with found postal departures
     */
    public final ArrayList<PostalDeparture>
    selectionLogActivityParcelByString(
            final String searchQuery) {

        ArrayList<PostalDeparture> foundPostalDepartures
                = new ArrayList<PostalDeparture>();
        String searchQueryString = searchQuery.toLowerCase();

        if (!searchQuery.isEmpty()) {

            for (ActionOnDeparture actionOnDeparture : logActivityParcels) {

                if ((actionOnDeparture.getPostalDeparture()
                        .getNameRecipient() != null)
                        && (actionOnDeparture.getPostalDeparture()
                        .getAdressRecipient() != null)) {

                    if (
                            ((actionOnDeparture.getPostalDeparture()
                                    .getNameRecipient()).toLowerCase()
                                    .indexOf(searchQueryString) >= 0)
                                    || ((actionOnDeparture.getPostalDeparture()
                                    .getAdressRecipient()).toLowerCase()
                                    .indexOf(searchQueryString) >= 0)
                            ) {

                        if (!foundPostalDepartures.contains(
                                actionOnDeparture.getPostalDeparture())) {
                            foundPostalDepartures.add(actionOnDeparture
                                    .getPostalDeparture());
                        }
                    }

                }

            }

        }

        Collections.sort(foundPostalDepartures);

        return foundPostalDepartures;

    }

    /**
     * Create clone object class PostalDepartment.
     * @return clone object
     * @throws CloneNotSupportedException exception
     */
    public final PostalDepartment clone() throws CloneNotSupportedException {

        return (PostalDepartment) super.clone();

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
        setNamePostDepartment(list.get(0).toString());
        setAdressPostDepartment(list.get(1).toString());
        setLogActivityParcels((ArrayList<ActionOnDeparture>) list.get(2));
    }

    /**
     * The method returns identifier class.
     * @return list variables
     */
    @JsonIgnore
    public final Integer getIdentifier() {
        return Integer.parseInt(getIndexPostDepartment());
    }

    /**
     * The method add variable identifier class.
     * @param identifier - variable identifier class
     */
    public final void setIdentifier(final Integer identifier) {
        setIndexPostDepartment(identifier.toString());
    }

    /**
     * The method returns class variables
     * that do not require additional serialization.
     * @return list variables
     */
    @JsonIgnore
    public final List<Object> getOrdinaryVariable() {
        List list = new ArrayList();
        list.add(getNamePostDepartment());
        list.add(getAdressPostDepartment());
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
        list.add(getLogActivityParcels());
        return list;
    }
}
