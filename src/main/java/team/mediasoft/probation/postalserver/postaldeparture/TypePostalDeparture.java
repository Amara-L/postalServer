package team.mediasoft.probation.postalserver.postaldeparture;

import javax.persistence.*;

/**
 * Variable enumeration type.
 */
public enum TypePostalDeparture {

    /**
     * Type LetterPostalDeparture.
     */
    @Column(name = "type")
    letter,

    /**
     * Type ParcelPostalDeparture.
     */
    parcel,

    /**
     * Type PostcardPostalDeparture.
     */
    postcard,

    /**
     * Type WrapperPostalDeparture.
     */
    wrapper;
}
