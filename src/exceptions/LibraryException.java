package exceptions;

/**
 * Represents an exception specific to library-related operations.
 */
public class LibraryException extends Exception {
    /**
     * Constructs a new LibraryException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public LibraryException(String message) {
        super(message);
    }
}
