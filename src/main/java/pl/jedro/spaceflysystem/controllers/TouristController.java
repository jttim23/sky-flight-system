package pl.jedro.spaceflysystem.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.jedro.spaceflysystem.api.DTO.TouristDTO;
import pl.jedro.spaceflysystem.exceptions.DeleteRequestInvalidException;
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
    @Operation(
            summary = "Get all flights from the tourist",
            description = "Use this endpoint to get all flights assigned to the tourist with specific id",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(
                            mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(
                            mediaType = "application/json"))
            },
            parameters = {@Parameter(description = "id of tourist", name = "id")

            }
    )


    @GetMapping("/{id}/flights")
    @ResponseStatus(HttpStatus.OK)
    public List<Flight> getTouristFlights(@PathVariable Long id) {
        return touristService.getTouristFlights(id);
    }


    @Operation(
            summary = "Get tourist with specific id",
            description = "Use this endpoint to get a tourist saved in db",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(
                            mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(
                            mediaType = "application/json"))
            },
            parameters = {@Parameter(description = "id of tourist", name = "id")}
    )
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TouristDTO getById(@PathVariable Long id) {
        return touristService.getTouristById(id);
    }


    @Operation(
            summary = "Get all tourists",
            description = "Use this endpoint to get all tourists saved in db",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Success")
            }
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Tourist> getAllTourist() {
        return touristService.getAllTourists();
    }


    @Operation(
            summary = "Create tourist",
            description = "Use this endpoint to create new tourist with post and parameter send as request body",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Success"),
                    @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(
                            mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(
                            mediaType = "application/json"))
            }
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Valid touristDTO", content = @Content(
            mediaType = "application/json"))
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TouristDTO createTourist(@RequestBody TouristDTO touristDTO) {
        return touristService.createTourist(touristDTO);
    }
    @Operation(
            summary = "Assign flight to tourist",
            description = "Use this endpoint to add flight by id to the tourist with specific id",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(
                            mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(
                            mediaType = "application/json")),
                    @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(
                            mediaType = "application/json"))
            },
            parameters = {@Parameter(description = "id of tourist", name = "touristId"),
                    @Parameter(description = "id of flight", name = "flightId", in = ParameterIn.QUERY)}
    )
    @PostMapping("/{touristId}/flights")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Flight> addFlightToTourist(@PathVariable Long touristId, @RequestParam(name = "flight_id") Long flightId) {
        return touristService.addFlightTOTourist(touristId, flightId);
    }


    @Operation(
            summary = "Delete tourist by specific id",
            description = "Use this endpoint to delete a tourist saved in db. Cannot use with swagger UI(redirect issues)",
            responses = {
        @ApiResponse(responseCode = "201", description = "Success"),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(
                mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(
                mediaType = "application/json"))
    },
    parameters = {@Parameter(description = "id of tourist", name = "id")}
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)

    public void deleteTourist(HttpServletResponse response, @PathVariable Long id) throws IOException {
        touristService.deleteTourist(id);
        response.sendRedirect(BASE_URL);
    }


    @Operation(
            summary = "Delete flight from tourist",
            description = "Use this endpoint to delete flight by id from the tourist with specific id. . Cannot use with swagger UI(redirect issues)",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(
                            mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(
                            mediaType = "application/json")),
                    @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(
                            mediaType = "application/json"))
            },
            parameters = {@Parameter(description = "id of tourist", name = "touristId"),
                    @Parameter(description = "id of flight", name = "flightId", in = ParameterIn.QUERY)}
    )
    @DeleteMapping("/{id}/flights")
    public void deleteFlightInTourist(HttpServletResponse response, @PathVariable Long touristId,
                                      @RequestParam(name = "flightId") Long flightId) throws IOException, DeleteRequestInvalidException {
        touristService.deleteFlightInTourist(flightId, flightId);
        response.sendRedirect(BASE_URL + "/" + flightId + "/flights");

    }

}
