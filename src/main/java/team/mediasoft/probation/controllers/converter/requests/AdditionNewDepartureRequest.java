package team.mediasoft.probation.controllers.converter.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Class-request addition new departure.
 */
public class AdditionNewDepartureRequest {

    /**
     * Variable type postal departure.
     */
    @JsonProperty("type")
    @Getter
    @Setter
    private String type;

    /**
     * Variable index recipient.
     */
    @JsonProperty("index")
    @Getter
    @Setter
    private String index;

    /**
     * Variable name recipient.
     */
    @JsonProperty("name")
    @Getter
    @Setter
    private String name;

    /**
     * Variable address recipient.
     */
    @JsonProperty("address")
    @Getter
    @Setter
    private String address;

    /**
     * Variable index postal department.
     */
    @JsonProperty("indexPostalOffice")
    @Getter
    @Setter
    private String indexPostalDepartment;

}
