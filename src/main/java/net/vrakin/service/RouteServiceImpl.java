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
public class RouteServiceImpl extends GeneralAbstractService<Route> implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Override
    protected void init() {
        this.repo = routeRepository;
    }

    @Override
    public List<Route> findByCity(City city1){
        return routeRepository.findByCity1OrCity2(city1, city1);
    }

    @Override
    public boolean checkUniqueName(String name) {
        return false;
    }
}
