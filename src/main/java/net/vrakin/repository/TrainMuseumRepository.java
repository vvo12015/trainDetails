package net.vrakin.repository;

import net.vrakin.model.TrainMuseum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainMuseumRepository extends CrudRepository<TrainMuseum, Long> {
}
