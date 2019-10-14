package team.mediasoft.probation.controllers.converter.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Class-request registration action departure.
 */
public class RegistrationActionRequest {

    /**
     * Variable action postal departure.
     */
    @JsonProperty("action")
    @Getter
    @Setter
    private String action;

    /**
     * Variable id postal departure.
     */
    @JsonProperty("idDeparture")
    @Getter
    @Setter
    private String idPostalDeparture;

    /**
     * Variable index postal department.
     */
    @JsonProperty("indexOffice")
    @Getter
    @Setter
    private String indexPostalDepartment;

}
