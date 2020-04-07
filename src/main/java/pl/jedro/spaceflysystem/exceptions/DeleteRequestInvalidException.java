package pl.jedro.spaceflysystem.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public final class DeleteRequestInvalidException extends RuntimeException {
    public DeleteRequestInvalidException() {
    }

    public DeleteRequestInvalidException(String message) {
        super(message);
    }

    public DeleteRequestInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteRequestInvalidException(Throwable cause) {
        super(cause);
    }

    public DeleteRequestInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
