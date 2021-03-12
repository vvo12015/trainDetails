package net.vrakin.service;

import lombok.extern.slf4j.Slf4j;
import net.vrakin.model.Cargo;
import net.vrakin.model.City;
import net.vrakin.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CityServiceImpl extends GeneralAbstractService<City> implements CityService{

    @Autowired
    private CityRepository cityRepository;

    @Override
    protected void init() {
        this.repo = cityRepository;
        log.debug("call method: init");
    }

    @Override
    public City findByName(String name) {
        if (cityRepository.findByName(name).isPresent()){
            City city = cityRepository.findByName(name).get();
            log.debug("call method: findByName with name: " + name + ". Found - " + city.getName());
        }
        log.debug("call method: findByName with name: " + name + ". No found");

        return null;
    }

    @Override
    public boolean checkUniqueName(String name) {
        boolean b = cityRepository.findByName(name).isPresent();
        log.debug("call method: checkUniqueName with name: " + name + ". Value - " + b);
        return b;
    }
}
