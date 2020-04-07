package pl.jedro.spaceflysystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourcePresentException extends RuntimeException {

    public ResourcePresentException() {
    }

    public ResourcePresentException(String message) {
        super(message);
    }

    public ResourcePresentException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourcePresentException(Throwable cause) {
        super(cause);
    }

    public ResourcePresentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
