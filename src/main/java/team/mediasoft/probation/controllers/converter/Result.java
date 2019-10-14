package team.mediasoft.probation.controllers.converter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Query result class Json-object.
 */
public class Result {

    /**
     * Variable status result.
     */
    @JsonProperty("status")
    @Getter
    private boolean status;

    /**
     * Variable message result.
     */
    @JsonProperty("message")
    @Getter
    private String message;

    /**
     * Setter variable message.
     * @param message message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Setter variable status.
     * @param status status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
}
