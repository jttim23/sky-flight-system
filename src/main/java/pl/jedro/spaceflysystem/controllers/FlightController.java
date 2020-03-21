package pl.jedro.spaceflysystem.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.jedro.spaceflysystem.api.DTO.FlightDTO;
import pl.jedro.spaceflysystem.services.FlightService;

import java.security.Provider;
import java.util.List;

@RestController
@RequestMapping(FlightController.BASE_URL)
public class FlightController {
    public static final String BASE_URL="/api/v1/flights";
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FlightDTO> getAllFlights(){
        return flightService.getAllFlights();
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFlightById(@PathVariable Long id){
        flightService.deleteFlight(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FlightDTO createFlight(FlightDTO flightDTO){
         return flightService.createFlight(flightDTO);
    }
}
