package net.vrakin.repository;

import net.vrakin.model.DetailMuseum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetailsMuseumRepository extends JpaRepository<DetailMuseum, Long> {
    Optional<DetailMuseum> findByName(String name);
}
