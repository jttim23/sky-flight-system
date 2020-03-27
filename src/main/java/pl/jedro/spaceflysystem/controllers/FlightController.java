package pl.jedro.spaceflysystem.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.jedro.spaceflysystem.api.DTO.FlightDTO;
import pl.jedro.spaceflysystem.exceptions.DeleteRequestInvalidException;
import pl.jedro.spaceflysystem.model.Flight;
import pl.jedro.spaceflysystem.model.Tourist;
import pl.jedro.spaceflysystem.services.FlightService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(FlightController.BASE_URL)
public class FlightController {
    public static final String BASE_URL = "/api/v1/flights";
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/{id}/tourists")
    public List<Tourist> getFlightTourists(@PathVariable Long id) {
        return flightService.getFlightTourists(id);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightDTO getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FlightDTO createFlight(@RequestBody FlightDTO flightDTO) {
        return flightService.createFlight(flightDTO);
    }

    @PostMapping("/{id}/tourists")
    public List<Tourist> addTouristToFlight(@PathVariable Long id, @RequestParam(name = "tourist_id") Long touristId) {
        return flightService.addTouristToFlight(id, touristId);
    }

    @DeleteMapping("/{id}/tourists")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTouristInFlight(HttpServletResponse response, @PathVariable Long id,
                                      @RequestParam(name = "tourist_id") Long touristId) throws IOException, DeleteRequestInvalidException {
        flightService.deleteTouristInFlight(id, touristId);
        response.sendRedirect(BASE_URL + "/" + id + "/tourists");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFlightById(HttpServletResponse response, @PathVariable Long id) throws IOException {
        flightService.deleteFlight(id);
        response.sendRedirect(BASE_URL);
    }


}
