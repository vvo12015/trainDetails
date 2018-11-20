package net.vrakin.service;

import net.vrakin.model.Cargo;

import java.util.List;

public interface CargoSevice extends GeneralService<Cargo>{

    List<Cargo> findByName(String name);
}
