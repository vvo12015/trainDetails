package net.vrakin.service;

import net.vrakin.model.City;
import net.vrakin.model.Route;
import net.vrakin.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public Route findById(Long id) {
        return routeRepository.findById(id).get();
    }

    @Override
    public List<Route> findByCity(City city1){
        return routeRepository.findByCity1OrCity2(city1, city1);
    }

    @Override
    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    @Override
    public void save(Route object) {
        routeRepository.save(object);
    }

    @Override
    public void delete(Route object) {
        routeRepository.delete(object);
    }

    @Override
    public List<Map<String, String>> findAllToMap() {
        return findAll().stream().map(Route::toMap).collect(Collectors.toList());
    }

    @Override
    public boolean checkUniqueName(String name) {
        return false;
    }
}
