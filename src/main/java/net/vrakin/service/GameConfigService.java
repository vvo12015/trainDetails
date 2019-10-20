package net.vrakin.service;

import net.vrakin.model.GameConfig;

import java.util.Optional;

public interface GameConfigService extends GeneralService<GameConfig> {
    Optional<GameConfig> findByName(String name);
}
