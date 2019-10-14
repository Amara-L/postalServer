package team.mediasoft.probation.postalserver.marshaller.parsertxt;

import java.util.List;

/**
 * Interface indicating opportunity serialization java-object in txt-file.
 */
public interface SerializableTXT {

    /**
     * Constructor for creating an object when reading from a txt-file.
     * @param list - the resulting list of variables is in order from the
     * transfer of getOrdinaryVariable() and getObjectVariable()
     * @throws Exception - an exception caused when accessing a non-existent
     * index of the list or the inability to cast an object
     */
    void serializationConstructor(List<Object> list) throws Exception;

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

    /**
     * The method returns class variables
     * that do not require additional serialization.
     * @return list variables
     */
    List<Object> getOrdinaryVariable();

    /**
     * The method returns class variables
     * that are object references.
     * @return list variables
     */
    List<Object> getObjectVariable();

}
