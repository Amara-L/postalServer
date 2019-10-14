package team.mediasoft.probation.postalserver.postaldeparture;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class-object wrapper postal departure.
 */
@XmlRootElement(name = "postalDepartures")
public class WrapperPostalDeparture extends UniversalPostalDeparture {

    /**
     * Default constructor class.
     */
    public WrapperPostalDeparture() {

        super();

    }

    /**
     * Constructor class.
     * @param indexRecipient - index postal departure
     * @param adressRecipient - adress postal departure
     * @param nameRecipient - name postal departure
     */
    public WrapperPostalDeparture(
            final String indexRecipient,
            final String adressRecipient,
            final String nameRecipient) {

        super(indexRecipient, adressRecipient, nameRecipient, "wrapper");

    }
}
