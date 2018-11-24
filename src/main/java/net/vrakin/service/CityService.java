package net.vrakin.service;

import net.vrakin.model.City;

import java.util.List;
import java.util.Optional;

public interface CityService extends GeneralService<City>{

    City findByName(String name);
}
