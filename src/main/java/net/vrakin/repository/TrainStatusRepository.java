package net.vrakin.repository;

import net.vrakin.model.TrainStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainStatusRepository extends JpaRepository<TrainStatus, Long> {
    Optional<TrainStatus> findByName(String name);
}
