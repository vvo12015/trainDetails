package net.vrakin.service;

import lombok.extern.slf4j.Slf4j;
import net.vrakin.model.Cargo;
import net.vrakin.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CargoServiceImpl extends GeneralAbstractService<Cargo> implements CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    @Override
    protected void init() {

        this.repo = cargoRepository;
        log.debug("call method: init");
    }

    @Override
    public Cargo findByName(String name) {
        if (cargoRepository.findByName(name).isPresent()){
            Cargo cargo = cargoRepository.findByName(name).get();
            log.debug("call method: findByName with name: " + name + ". Found - " + cargo.getName());
        }
        log.debug("call method: findByName with name: " + name + ". No found");

        return null;
    }

    @Override
    public boolean checkUniqueName(String name) {
        boolean b = cargoRepository.findByName(name).isPresent();
        log.debug("call method: checkUniqueName with name: " + name + ". Value - " + b);
        return b;
    }
}
