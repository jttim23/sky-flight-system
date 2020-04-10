package pl.jedro.spaceflysystem.exceptions;

public class FlightNotFoundException extends RuntimeException {
    public FlightNotFoundException() {
    }

    public FlightNotFoundException(String message) {
        super(message);
    }

}
