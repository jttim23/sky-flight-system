package pl.jedro.spaceflysystem.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.jedro.spaceflysystem.controllers.FlightController;
import pl.jedro.spaceflysystem.controllers.TouristController;

import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity<Object> handleFlightNotFoundException(Exception exception, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalTime.now());
        body.put("message", "Flight Not Found, try another Id");
        body.put("return_to_home_page", FlightController.BASE_URL);

        return new ResponseEntity<Object>(body, new HttpHeaders(), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(TouristNotFoundException.class)
    public ResponseEntity<Object> handleTouristNotFoundException(Exception exception, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalTime.now());
        body.put("message", "Tourist Not Found, try another Id");
        body.put("return_to_home_page", TouristController.BASE_URL);

        return new ResponseEntity<Object>(body, new HttpHeaders(), HttpStatus.NOT_FOUND);

    }
}
