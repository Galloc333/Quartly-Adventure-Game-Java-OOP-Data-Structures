/**
 * Exception indicating that the exit in a room is already occupied.
 */
public class ExitIsOccupied extends RuntimeException {

    /**
     * Constructs an ExitIsOccupied with no detail message.
     */
    public ExitIsOccupied() {
        super();
    }

    /**
     * Constructs an ExitIsOccupied with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ExitIsOccupied(String message) {
        super(message);
    }

    /**
     * Constructs an ExitIsOccupied with the specified detail message and cause.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     * @param cause   the cause (which is saved for later retrieval by the getCause() method).
     */
    public ExitIsOccupied(String message, Throwable cause) {
        super(message, cause);
    }
}

