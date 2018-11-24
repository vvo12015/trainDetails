package net.vrakin.service;

import net.vrakin.model.Cargo;
import net.vrakin.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CargoServiceImpl extends GeneralAbstractService<Cargo> implements CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    @Override
    protected void init() {
        this.repo = cargoRepository;
    }

    @Override
    public Cargo findByName(String name) {
        return cargoRepository.findByName(name).get();
    }

    @Override
    public boolean checkUniqueName(String name) {
        return cargoRepository.findByName(name).isPresent();
    }
}
