package exceptions;

import java.util.UUID;

/**
 * Represents an exception thrown when a user is underage for a particular operation.
 * Extends LibraryException.
 */
public class UnderageException extends LibraryException {
    private final UUID uuid;
    private final int requiredAge;

    /**
     * Gets the UUID of the underage user.
     *
     * @return The UUID of the underage user.
     */
    public UUID getUuid() { return this.uuid; }
    /**
     * Gets the minimum required age for the operation.
     *
     * @return The minimum required age for the operation.
     */
    public int getRequiredAge() { return this.requiredAge; }

    /**
     * Constructs a new UnderageException with the specified required age and UUID.
     *
     * @param requiredAge The minimum required age for the operation.
     * @param uuid        The UUID of the underage user.
     */
    public UnderageException(int requiredAge, UUID uuid) {
        super("User is below the minimum age requirement (" + requiredAge + ") for the given object.");

        this.uuid = uuid;
        this.requiredAge = requiredAge;
    }
}
