package net.vrakin.service;

import net.vrakin.model.Cargo;

import java.util.Optional;

public interface CargoService extends GeneralService<Cargo>{

    Cargo findByName(String name);
    boolean checkUniqueName(String name);
}
