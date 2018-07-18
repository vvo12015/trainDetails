package net.vrakin.repository;

import net.vrakin.model.Train;
import net.vrakin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainRepository extends JpaRepository<Train, Long> {

    List<Train> findByUser(User user);
}
