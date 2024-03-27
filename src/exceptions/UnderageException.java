package exceptions;

import java.util.UUID;

public class UnderageException extends LibraryException {
    private final UUID uuid;
    private final int requiredAge;

    public UUID getUuid() { return this.uuid; }
    public int getRequiredAge() { return this.requiredAge; }

    public UnderageException(int requiredAge, UUID uuid) {
        super("User is below the minimum age requirement (" + requiredAge + ") for the given object.");

        this.uuid = uuid;
        this.requiredAge = requiredAge;
    }
}
