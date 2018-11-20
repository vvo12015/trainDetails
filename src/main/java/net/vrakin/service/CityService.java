package net.vrakin.service;

import net.vrakin.model.City;

import java.util.List;

public interface CityService extends GeneralService<City>{

    List<City> findByName(String name);
}
