/**
 * Exception indicating that a room does not exist.
 */
public class RoomDoesNotExist extends RuntimeException {

    /**
     * Constructs a RoomDoesNotExist with no detail message.
     */
    public RoomDoesNotExist() {
        super();
    }

    /**
     * Constructs a RoomDoesNotExist with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     */
    public RoomDoesNotExist(String message) {
        super(message);
    }

    /**
     * Constructs a RoomDoesNotExist with the specified detail message and cause.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     * @param cause   the cause (which is saved for later retrieval by the getCause() method).
     */
    public RoomDoesNotExist(String message, Throwable cause) {
        super(message, cause);
    }
}
