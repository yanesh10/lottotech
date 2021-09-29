package mu.yanesh.lotto.library.exception;

public class MongoDataAccessException extends RuntimeException {

    public MongoDataAccessException(String message) {
        super(message);
    }

    public MongoDataAccessException(String message, String text) {
        super(message + text);
    }

    public MongoDataAccessException(String message, String text, Throwable throwable) {
        super(message + text, throwable);
    }
}
