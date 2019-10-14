package team.mediasoft.probation.postalserver.postaldeparture;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class-object postcard postal departure.
 */
@XmlRootElement(name = "postalDepartures")
public class PostcardPostalDeparture extends UniversalPostalDeparture {

    /**
     * Default constructor class.
     */
    public PostcardPostalDeparture() {

        super();
    }

    /**
     * Constructor class.
     * @param indexRecipient - index postal departure
     * @param adressRecipient - adress postal departure
     * @param nameRecipient - name postal departure
     */
    public PostcardPostalDeparture(
            final String indexRecipient,
            final String adressRecipient,
            final String nameRecipient) {

        super(indexRecipient, adressRecipient, nameRecipient, "postcard");

    }
}
