package team.mediasoft.probation.controllers.converter;

/**
 * Creation class response with error messages.
 */
public class CreatedResponseError {

    /**
     * Method create class Response with error.
     * @param message - error message
     * @return - created object Response
     */
    public Response createError(String message) {

        Result jsonResult = new Result();
        jsonResult.setStatus(false);

        if (message != null) {
            jsonResult.setMessage(message);
        }

        Response jsonResponse = new Response();

        jsonResponse.setResult(jsonResult);

        return jsonResponse;
    }

}
