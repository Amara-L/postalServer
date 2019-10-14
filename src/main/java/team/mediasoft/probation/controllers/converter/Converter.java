package team.mediasoft.probation.controllers.converter;

import java.io.IOException;

/**
 * Converter class interface for converting Java objects to DOM objects.
 */
public interface Converter {

    /**
     * Marshaller to DOM object.
     * @param response - object class Response
     * @return - String DOM object
     * @throws IOException - exception marshaller
     */
    String convertTo(Response response) throws IOException;

    /**
     * Unmarshaller to Java Object.
     * @param textString - String DOM object
     * @param classObject - class Java Object
     * @return object class
     * @throws IOException - exception marshaller
     */
    Object convertToJavaObject(String textString, Class classObject)
            throws IOException;

}
