package net.vrakin.repository;

import net.vrakin.model.OrderState;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderStateRepository extends JpaRepository<OrderState, Long> {

    Optional<OrderState> findByName(String name);
}
