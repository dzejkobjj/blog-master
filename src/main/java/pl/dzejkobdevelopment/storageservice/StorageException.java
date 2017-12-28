package pl.dzejkobdevelopment.storageservice;

public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message + cause.getMessage(), cause);
    }
}
