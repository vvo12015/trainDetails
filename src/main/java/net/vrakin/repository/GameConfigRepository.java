package net.vrakin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.vrakin.model.GameConfig;

import java.util.Optional;

@Repository
public interface GameConfigRepository extends JpaRepository<GameConfig, Long> {
    Optional<GameConfig> findByName(String name);
}
