package net.vrakin.repository;

import net.vrakin.model.DetailMuseum;
import net.vrakin.model.TrainMuseum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainMuseumRepository extends JpaRepository<TrainMuseum, Long> {
    Optional<TrainMuseum> findByName(String name);
    List<TrainMuseum> findByDetails(List<DetailMuseum> detailMuseumList);
}
