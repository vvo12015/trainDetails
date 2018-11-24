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
public class CityServiceImpl implements CityService{

    @Autowired
    private CityRepository cityRepository;

    @Override
    public City findById(Long id) {
        return cityRepository.findById(id).get();
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public City findByName(String name) {
        return cityRepository.findByName(name).get();
    }

    @Override
    public void save(City object) {
        cityRepository.save(object);
    }

    @Override
    public void delete(City object) {
        cityRepository.delete(object);
    }

    @Override
    public List<Map<String, String>> findAllToMap() {
        return findAll().stream().map(City::toMap).collect(Collectors.toList());
    }

    @Override
    public boolean checkUniqueName(String name) {
        return cityRepository.findByName(name).isPresent();
    }
}
