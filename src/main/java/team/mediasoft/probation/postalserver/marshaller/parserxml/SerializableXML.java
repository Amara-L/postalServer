package team.mediasoft.probation.postalserver.marshaller.parserxml;

/**
 * Interface indicating opportunity serialization java-object in xml-file.
 */
public interface SerializableXML {

    /**
     * The method returns identifier class.
     * @return list variables
     */
    Integer getIdentifier();

    /**
     * The method add variable identifier class.
     * @param identifier - variable identifier class
     */
    void setIdentifier(Integer identifier);

}
