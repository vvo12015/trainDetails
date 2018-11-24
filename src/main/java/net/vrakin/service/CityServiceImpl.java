package net.vrakin.service;

import net.vrakin.model.City;
import net.vrakin.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl extends GeneralAbstractService<City> implements CityService{

    @Autowired
    private CityRepository cityRepository;

    @Override
    protected void init() {
        this.repo = cityRepository;
    }

    @Override
    public City findByName(String name) {
        return cityRepository.findByName(name).get();
    }

    @Override
    public boolean checkUniqueName(String name) {
        return cityRepository.findByName(name).isPresent();
    }
}
