package net.vrakin.service;

import net.vrakin.model.Cargo;
import net.vrakin.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CargoSeviceImpl implements CargoSevice {

    @Autowired
    private CargoRepository cargoRepository;

    @Override
    public Cargo findById(Long id) {
        return cargoRepository.findById(id).get();
    }

    @Override
    public List<Cargo> findAll() {
        return cargoRepository.findAll();
    }

    @Override
    public List<Cargo> findByName(String name) {
        return cargoRepository.findByName(name);
    }

    @Override
    public void save(Cargo object) {
        cargoRepository.save(object);
    }

    @Override
    public void delete(Cargo object) {
        cargoRepository.delete(object);
    }

    @Override
    public List<Map<String, String>> findAllToMap() {
        return findAll().stream().map(Cargo::toMap).collect(Collectors.toList());
    }
}
