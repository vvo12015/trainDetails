package net.vrakin.service;

import lombok.extern.slf4j.Slf4j;
import net.vrakin.model.City;
import net.vrakin.model.Route;
import net.vrakin.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RouteServiceImpl extends GeneralAbstractService<Route> implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Override
    protected void init() {
        log.debug("call method: init");

        this.repo = routeRepository;
    }

    @Override
    public List<Route> findByCity(City city1){
        List<Route> routes = routeRepository.findByCity1OrCity2(city1, city1);

        log.debug("call method: findByCity with city " + city1.getName() + ". Received route count " + routes.size());
        return routes;
    }

    @Override
    public boolean checkUniqueName(String name) {
        log.debug("call method checkUniqueName with route: " + name +
                ". Received const value false");
        return false;
    }
}
