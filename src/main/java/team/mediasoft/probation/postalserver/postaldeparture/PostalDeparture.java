package team.mediasoft.probation.postalserver.postaldeparture;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * Class-object postal departure.
 */
@XmlType
public abstract class PostalDeparture implements Comparable<PostalDeparture>,
        Serializable, Cloneable {

    /**
     * Variable id postal departure.
     */
    @Setter
    @Getter
    private int id;

    /**
     * Variable index postal departure.
     */
    @Setter
    @Getter
    private String indexRecipient;

    /**
     * Variable adress postal departure.
     */
    @Setter
    @Getter
    private String adressRecipient;

    /**
     * Variable name postal departure.
     */
    @Setter
    @Getter
    private String nameRecipient;

    /**
     * Default constructor class.
     */
    public PostalDeparture() {

        this.setId(0);
        this.setIndexRecipient(null);
        this.setAdressRecipient(null);
        this.setNameRecipient(null);
    }

    /**
     * Constructor class.
     * @param receivedIndexRecipient - index postal departure
     * @param receivedAdressRecipient - adress postal departure
     * @param receivedNameRecipient - name postal departure
     */
    public PostalDeparture(
            final String receivedIndexRecipient,
            final String receivedAdressRecipient,
            final String receivedNameRecipient
    ) {

        this.setIndexRecipient(receivedIndexRecipient);
        this.setAdressRecipient(receivedAdressRecipient);
        this.setNameRecipient(receivedNameRecipient);

    }

    /**
     * Getter for get variable type postal departure.
     * The method compares the current object with the received one.
     * Returns a positive number if the current object is
     * larger and negative if less.
     * If the equality is 0. When getting a null object,
     * a null object is taken as a larger object.
     * @return variable type
     */
    abstract String getType();

    /**
     * Method overload compareTo.
     * @param nextPostalDeparture next object
     * @return result
     */
    public final int compareTo(
            final PostalDeparture nextPostalDeparture) {

        int result = 0;

        if ((nextPostalDeparture != null)) {

            if (nextPostalDeparture.getNameRecipient() != null) {

                if (this.getNameRecipient() != null) {

                    result = this.getNameRecipient()
                            .compareTo(nextPostalDeparture
                                    .getNameRecipient());
                } else {

                    result = 1;
                }

            } else {

                if (this.getNameRecipient() != null) {

                    result = -1;

                }
            }

        } else if (this.getNameRecipient() != null) {
            result = -1;
        }

        return result;
    }

    /**
     * Create clone object class PostalDeparture.
     * @return clone object
     * @throws CloneNotSupportedException exception
     */
    public final PostalDeparture clone() throws CloneNotSupportedException {

        return (PostalDeparture) super.clone();

    }

}
