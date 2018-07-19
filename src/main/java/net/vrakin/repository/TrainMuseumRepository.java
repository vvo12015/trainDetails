package net.vrakin.repository;

import net.vrakin.model.TrainMuseum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainMuseumRepository extends JpaRepository<TrainMuseum, Long> {

}
