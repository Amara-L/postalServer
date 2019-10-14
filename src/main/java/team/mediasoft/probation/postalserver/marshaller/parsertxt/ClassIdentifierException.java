package team.mediasoft.probation.postalserver.marshaller.parsertxt;

/**
 * Exception caused by the absence of an identifier in the class.
 */
public class ClassIdentifierException extends Exception {

    /**
     * Exception constructor.
     * @param message error message
     */
    public ClassIdentifierException(final String message) {
        super(message);
    }

}
