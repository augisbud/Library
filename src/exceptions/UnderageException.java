package exceptions;

import java.util.UUID;

public class UnderageException extends Exception {
    private final UUID uuid;

    public UUID getUuid() { return this.uuid; }

    public UnderageException(int requiredAge, UUID uuid) {
        super("User is below the minimum age requirement (" + requiredAge + ") for the given object.");

        this.uuid = uuid;
    }
}
