package team.mediasoft.probation.postalserver.postaldeparture;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class-object parcel postal departure.
 */
@XmlRootElement(name = "postalDepartures")
public class ParcelPostalDeparture extends UniversalPostalDeparture {

    /**
     * Default constructor class.
     */
    public ParcelPostalDeparture() {

        super();
    }

    /**
     * Constructor class.
     * @param indexRecipient - index postal departure
     * @param adressRecipient - adress postal departure
     * @param nameRecipient - name postal departure
     */
    public ParcelPostalDeparture(
            final String indexRecipient,
            final String adressRecipient,
            final String nameRecipient) {

        super(indexRecipient, adressRecipient, nameRecipient, "parcel");

    }

}
