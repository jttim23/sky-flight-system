package pl.jedro.spaceflysystem.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.jedro.spaceflysystem.api.DTO.TouristDTO;
import pl.jedro.spaceflysystem.model.Flight;
import pl.jedro.spaceflysystem.model.Tourist;
import pl.jedro.spaceflysystem.services.TouristService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(TouristController.BASE_URL)
public class TouristController {
    private final TouristService touristService;
    public static final String BASE_URL = "/api/v1/tourists";

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @GetMapping("/{id}/flights")
    @ResponseStatus(HttpStatus.OK)
    public List<Flight> getTouristFlights(@PathVariable Long id) {
        return touristService.getTouristFlights(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TouristDTO getById(@PathVariable Long id) {
        return touristService.getTouristById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Tourist> getAllTourist() {
        return touristService.getAllTourists();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TouristDTO createTourist(@RequestBody TouristDTO touristDTO) {
        return touristService.createTourist(touristDTO);
    }

    @PostMapping("/{id}/flights")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Flight> addFlightToTourist(@PathVariable Long id, @RequestParam(name = "flight_id") Long flightId) {
        return touristService.addFlightTOTourist(id, flightId);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)

    public void deleteTourist(HttpServletResponse response, @PathVariable Long id) throws IOException {
        touristService.deleteTouristById(id);
        response.sendRedirect(BASE_URL);
    }

    @DeleteMapping("/{id}/flights")
    public void deleteFlightInTourist(HttpServletResponse response, @PathVariable Long id,
                                      @RequestParam(name = "flight_id") Long flightId) throws IOException {
        touristService.deleteFlightInTourist(id, flightId);
        response.sendRedirect(BASE_URL + "/" + id + "/flights");

    }

}
