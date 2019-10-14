package team.mediasoft.probation.postalserver.postaldeparture;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class-object letter postal departure.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "postalDepartures")
public class LetterPostalDeparture extends UniversalPostalDeparture {

    /**
     * Default constructor class.
     */
    public LetterPostalDeparture() {

        super();
    }

    /**
     * Constructor class.
     * @param indexRecipient - index postal departure
     * @param adressRecipient - adress postal departure
     * @param nameRecipient - name postal departure
     */
    public LetterPostalDeparture(
            final String indexRecipient,
            final String adressRecipient,
            final String nameRecipient) {

        super(indexRecipient, adressRecipient, nameRecipient, "letter");

    }

}
