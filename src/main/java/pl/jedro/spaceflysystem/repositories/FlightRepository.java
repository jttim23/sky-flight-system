package pl.jedro.spaceflysystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jedro.spaceflysystem.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
