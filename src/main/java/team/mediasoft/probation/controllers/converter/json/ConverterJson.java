package team.mediasoft.probation.controllers.converter.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import team.mediasoft.probation.controllers.converter.Converter;
import team.mediasoft.probation.controllers.converter.Response;

import java.io.IOException;

/**
 * Converter class interface for converting Java objects to JSON objects.
 */
public class ConverterJson implements Converter {

    /**
     * Marshaller to DOM object.
     * @param response - object class Response
     * @return - String DOM object
     * @throws IOException - exception marshaller
     */
    public String convertTo(Response response) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        String jsonString = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(response);

        return jsonString;
    }

    /**
     * Unmarshaller to Java Object.
     * @param textString - String DOM object
     * @param classObject - class Java Object
     * @return object class
     * @throws IOException - exception marshaller
     */
    public Object convertToJavaObject(String textString, Class classObject)
            throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        Object object
                = mapper.readValue(textString, classObject);

        return object;
    }

}
