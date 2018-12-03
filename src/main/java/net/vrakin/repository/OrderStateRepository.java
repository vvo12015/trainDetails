package net.vrakin.repository;

import net.vrakin.model.OrderState;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderStateRepository extends JpaRepository<OrderState, Long> {

    Optional<OrderState> findByName(String name);

    List<OrderState> findByInMotion(boolean inMotion);
}
