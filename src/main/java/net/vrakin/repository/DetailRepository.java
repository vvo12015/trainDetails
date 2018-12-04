package net.vrakin.repository;

import net.vrakin.model.Detail;
import net.vrakin.model.DetailMuseum;
import net.vrakin.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {
    Optional<Detail> findByName(String name);

    List<Detail> findByTrain(Train train);
}
