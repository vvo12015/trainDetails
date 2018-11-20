package net.vrakin.service;

import net.vrakin.model.City;
import net.vrakin.model.Route;

import java.util.List;

public interface RouteService extends GeneralService<Route>{

    List<Route> findByCity(City city1);
}
