package net.vrakin.repository;

import net.vrakin.model.DetailMuseum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DetailsMuseumRepository extends JpaRepository<DetailMuseum, Long> {
    Optional<DetailMuseum> findByName(String name);
}
