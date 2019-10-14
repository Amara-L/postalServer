package team.mediasoft.probation.controllers.converter.xml;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import team.mediasoft.probation.controllers.converter.Converter;
import team.mediasoft.probation.controllers.converter.Response;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Converter class interface for converting Java objects to XML objects.
 */
public class ConverterXml implements Converter {

    /**
     * Variable Marshaller.
     * Loaded by container from context.
     */
    @Autowired
    @Qualifier("marshallerXML")
    @Setter
    @Getter
    private Marshaller marshaller;

    /**
     * Variable Unmarshaller.
     * Loaded by container from context.
     */
    @Autowired
    @Qualifier("unmarshallerXML")
    @Setter
    @Getter
    private Unmarshaller unmarshaller;

    /**
     * Marshaller to DOM object.
     * @param response - object class Response
     * @return - String DOM object
     * @throws IOException - exception marshaller
     */
    @Override
    public String convertTo(Response response) throws IOException {

        StringWriter writer = new StringWriter();
        StreamResult streamResult = new StreamResult(writer);
        String xmlText = null;

        marshaller.marshal(response, streamResult);

        xmlText = streamResult.getWriter().toString();

        return xmlText;

    }

    /**
     * Unmarshaller to Java Object.
     * @param textString - String DOM object
     * @param classObject - class Java Object
     * @return object class
     * @throws IOException - exception marshaller
     */
    @Override
    public Object convertToJavaObject(String textString, Class classObject)
            throws IOException {

        StringReader reader = new StringReader(textString);

        return unmarshaller.unmarshal(new StreamSource(reader));

    }

}
