package pl.jedro.spaceflysystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jedro.spaceflysystem.model.Tourist;

public interface TouristRepository extends JpaRepository<Tourist, Long> {
}
