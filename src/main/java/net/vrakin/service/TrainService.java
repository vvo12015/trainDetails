package net.vrakin.service;

import net.vrakin.model.Company;
import net.vrakin.model.Train;

import java.util.List;

public interface TrainService {

    void save(Train train);
    List<Train> findAll();
    void deleteByCompany(Company company);
    void findByCompany(Company company);
}
