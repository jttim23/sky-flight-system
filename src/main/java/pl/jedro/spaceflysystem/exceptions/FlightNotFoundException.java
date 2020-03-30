package pl.jedro.spaceflysystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;


public class FlightNotFoundException extends RuntimeException {
    public FlightNotFoundException() {
    }

    public FlightNotFoundException(String message) {
        super(message);
    }

}
