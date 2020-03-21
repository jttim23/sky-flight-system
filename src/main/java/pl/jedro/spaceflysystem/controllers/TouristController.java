package pl.jedro.spaceflysystem.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.jedro.spaceflysystem.api.DTO.TouristDTO;
import pl.jedro.spaceflysystem.services.TouristService;

import java.util.List;

@RestController
@RequestMapping(TouristController.BASE_URL)
public class TouristController {
    private final TouristService touristService;
    public static final String BASE_URL="/api/v1/tourists";

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TouristDTO> getAllTourist(){
      return   touristService.getAllTourists();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TouristDTO createTourist(@RequestBody TouristDTO touristDTO){
        return touristService.createTourist(touristDTO);
    }
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteTourist(@PathVariable Long id){
        touristService.deleteTouristById(id);
    }

}
