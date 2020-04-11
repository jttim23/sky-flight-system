package pl.jedro.spaceflysystem.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.jedro.spaceflysystem.api.DTO.FlightDTO;
import pl.jedro.spaceflysystem.exceptions.FlightNotFoundException;
import pl.jedro.spaceflysystem.model.Flight;
import pl.jedro.spaceflysystem.model.Tourist;
import pl.jedro.spaceflysystem.services.FlightService;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping(FlightController.BASE_URL)
public class FlightController {
    public static final String BASE_URL = "/api/v1/flights";
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @Operation(
            summary = "Get all tourists from the flight",
            description = "Use this endpoint to get all tourists assigned to the flight with specific id",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(
                            mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(
                            mediaType = "application/json"))
            },
            parameters = {@Parameter(description = "id of flight", name = "id")

            }
    )
    @GetMapping("/{id}/tourists")
    public List<Tourist> getFlightTourists(@PathVariable Long id) {
        try {
            return flightService.getFlightTourists(id);
        } catch (EntityNotFoundException ex) {
            throw new FlightNotFoundException();
        }
    }


    @Operation(
            summary = "Get all flights",
            description = "Use this endpoint to get all flights saved in db",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Success")
            }
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }


    @Operation(
            summary = "Get flight with specific id",
            description = "Use this endpoint to get a flight saved in db",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(
                            mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(
                            mediaType = "application/json"))
            },
            parameters = {@Parameter(description = "id of flight", name = "id")}
    )
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightDTO getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id);
    }


    @Operation(
            summary = "Create flight",
            description = "Use this endpoint to create new flight with post and parameter send as request body",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Success"),
                    @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(
                            mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(
                            mediaType = "application/json"))
            }
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Valid FlightDTO", content = @Content(
            mediaType = "application/json"))
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FlightDTO createFlight(@RequestBody FlightDTO flightDTO) {
        return flightService.createFlight(flightDTO);
    }


    @Operation(
            summary = "Assign tourist to flight",
            description = "Use this endpoint to add tourist by id to the flight with specific id",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(
                            mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(
                            mediaType = "application/json")),
                    @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(
                            mediaType = "application/json"))
            },
            parameters = {@Parameter(description = "id of flight", name = "flightId"),
                    @Parameter(description = "id of tourist", name = "touristId", in = ParameterIn.QUERY)}
    )
    @PostMapping("/{flightId}/tourists")
    public List<Tourist> addTouristToFlight(@PathVariable Long flightId, @RequestParam(name = "touristId") Long touristId) throws FlightNotFoundException {
        try {
            return flightService.addTouristToFlight(flightId, touristId);
        } catch (NoSuchElementException e) {
            throw new FlightNotFoundException("No flight with id: " + flightId + " or tourist with id:" + touristId + " exists.");
        }
    }


    @Operation(
            summary = "Delete tourist from flight",
            description = "Use this endpoint to delete tourist by id from the flight with specific id. . Cannot use with swagger UI(redirect issues)",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(
                            mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(
                            mediaType = "application/json")),
                    @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(
                            mediaType = "application/json"))
            },
            parameters = {@Parameter(description = "id of flight", name = "flightId"),
                    @Parameter(description = "id of tourist", name = "touristId", in = ParameterIn.QUERY)}
    )
    @DeleteMapping("/{id}/tourists")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTouristInFlight(HttpServletResponse response, @PathVariable Long flightId,
                                      @RequestParam(name = "touristId") Long touristId) throws IOException {
        flightService.deleteTouristInFlight(flightId, touristId);
        response.sendRedirect(BASE_URL + "/" + flightId + "/tourists");
    }


    @Operation(
            summary = "Delete flight by specific id",
            description = "Use this endpoint to delete a flight saved in db. Cannot use with swagger UI(redirect issues)",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(
                            mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(
                            mediaType = "application/json"))
            },
            parameters = {@Parameter(description = "id of flight", name = "id")}
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFlightById(HttpServletResponse response, @PathVariable Long id) throws IOException {
        flightService.deleteFlight(id);
        response.sendRedirect(BASE_URL);
    }


}
