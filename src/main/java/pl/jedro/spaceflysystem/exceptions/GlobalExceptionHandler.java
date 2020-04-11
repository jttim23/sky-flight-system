package pl.jedro.spaceflysystem.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.jedro.spaceflysystem.controllers.FlightController;
import pl.jedro.spaceflysystem.controllers.TouristController;

import javax.validation.ConstraintViolationException;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(Exception exception, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalTime.now());
        body.put("message", exception.getMessage());
        body.put("return_to_home_page", TouristController.BASE_URL);

        return new ResponseEntity<Object>(body, new HttpHeaders(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodMismatchException(Exception exception, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalTime.now());
        body.put("message", "Please provide suitable parameter ");
        body.put("return_to_home_page", TouristController.BASE_URL);

        return new ResponseEntity<Object>(body, new HttpHeaders(), HttpStatus.BAD_REQUEST);

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
