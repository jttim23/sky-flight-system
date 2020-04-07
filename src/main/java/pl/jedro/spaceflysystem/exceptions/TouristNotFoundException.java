package pl.jedro.spaceflysystem.exceptions;

public class TouristNotFoundException extends RuntimeException {
    public TouristNotFoundException() {
    }

    public TouristNotFoundException(String message) {
        super(message);
    }

    public TouristNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TouristNotFoundException(Throwable cause) {
        super(cause);
    }

    public TouristNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
