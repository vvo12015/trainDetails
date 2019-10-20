package net.vrakin.service;

import net.vrakin.model.GameConfig;
import net.vrakin.repository.GameConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GameConfigServiceImpl  extends GeneralAbstractService<GameConfig> implements GameConfigService {

    @Autowired
    protected GameConfigRepository gameConfigRepository;

    @Override
    public Optional<GameConfig> findByName(String name) {
        return gameConfigRepository.findByName(name);
    }

    @Override
    protected void init() {
        this.repo = gameConfigRepository;
    }

    @Override
    public boolean checkUniqueName(String name) {
        return gameConfigRepository.findByName(name).isPresent();
    }
}
