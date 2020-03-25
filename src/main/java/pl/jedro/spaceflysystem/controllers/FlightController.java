package pl.jedro.spaceflysystem.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.jedro.spaceflysystem.api.DTO.FlightDTO;
import pl.jedro.spaceflysystem.api.DTO.TouristDTO;
import pl.jedro.spaceflysystem.model.Flight;
import pl.jedro.spaceflysystem.services.FlightService;

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
    public List<TouristDTO> getFlightTourists(@PathVariable Long id) {
        return flightService.getFlightTourists(id);
    }
    @PostMapping("/{id}/tourists")
    public List<TouristDTO> addTouristtoFlight(@PathVariable Long id,@RequestParam(name = "tourist_id")Long touristId){
        return flightService.addTouristToFlight(id,touristId);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FlightDTO> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightDTO getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id);
    }

    @GetMapping("/test/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Flight getFlightByIdTest(@PathVariable Long id) {
        return flightService.getFlightByIdTest(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFlightById(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FlightDTO createFlight(@RequestBody FlightDTO flightDTO) {
        return flightService.createFlight(flightDTO);
    }
}
