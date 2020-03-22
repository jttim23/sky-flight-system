package pl.jedro.spaceflysystem.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.jedro.spaceflysystem.model.Flight;
import pl.jedro.spaceflysystem.model.Tourist;
import pl.jedro.spaceflysystem.repositories.FlightRepository;
import pl.jedro.spaceflysystem.repositories.TouristRepository;

@Component
public class Bootstrap implements CommandLineRunner {

    private final FlightRepository flightRepository;
    private final TouristRepository touristRepository;

    public Bootstrap(FlightRepository flightRepository, TouristRepository touristRepository) {
        this.flightRepository = flightRepository;
        this.touristRepository = touristRepository;
    }


    @Override
    public void run(String... args) throws Exception {

//        loadTourists();
//        loadFlights();
    }

    private void loadTourists() {
        Tourist tourist1 = new Tourist();
        tourist1.setId(1L);
        tourist1.setName("Jimmy");
        Tourist tourist2 = new Tourist();
        tourist2.setId(2L);
        tourist2.setName("Eve");
        touristRepository.save(tourist1);
        touristRepository.save(tourist2);
    }

    private void loadFlights() {
        Flight flight1 = new Flight();
        flight1.setSeatQuantity(6);
        flight1.setId(1L);
        Flight flight2 = new Flight();
        flight2.setSeatQuantity(5);
        flight2.setId(2L);
        flightRepository.save(flight1);
        flightRepository.save(flight2);
    }

}