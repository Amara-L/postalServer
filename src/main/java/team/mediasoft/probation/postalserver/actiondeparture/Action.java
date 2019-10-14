package team.mediasoft.probation.postalserver.actiondeparture;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * Variable enumeration actions.
 */
@XmlEnum
@XmlType(name = "actiontype")
public enum Action {
        /**
         * Action registration arrival.
         */
        registrationArrival,
        /**
         * Action registration dispatch.
         */
        registrationDispatch,
        /**
         * Action registration new departure.
         */
        registrationNewDeparture,
        /**
         * Action registration received by the addressee.
         */
        receivedByTheAddressee;

};

