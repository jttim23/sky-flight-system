package pl.jedro.spaceflysystem.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.jedro.spaceflysystem.model.Flight;
import pl.jedro.spaceflysystem.services.FlightService;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {
    private final FlightService flightService;

    public HomeController(FlightService flightService) {
        this.flightService = flightService;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

}
