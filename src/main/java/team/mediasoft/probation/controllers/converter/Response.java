package team.mediasoft.probation.controllers.converter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import team.mediasoft.probation.services.entity.EntityInterface;

/**
 * Json-object class response.
 */
public class Response {

    /**
     * Variable-object class Result.
     */
    @JsonProperty("requestResult")
    @Getter
    private Result result;

    /**
     * Variable-object class-heir EntityInterface.
     */
    @JsonProperty("responseData")
    @Getter
    private EntityInterface dataResult;

    /**
     * Setter variable result.
     * @param result result
     */
    public void setResult(Result result) {
        this.result = result;
    }

    /**
     * Setter variable dataResult.
     * @param objectResult dataResult
     */
    public void setDataResult(EntityInterface objectResult) {
        this.dataResult = objectResult;
    }
}
