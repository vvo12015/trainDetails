package net.vrakin.service;

import net.vrakin.model.Company;
import net.vrakin.model.Train;
import net.vrakin.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainRepository trainRepository;

    @Override
    public void deleteByCompany(Company company) {
        trainRepository.deleteByCompany(company);
    }

    @Override
    public List<Train> findAll() {
        return trainRepository.findAll();
    }

    @Override
    public void save(Train train) {
        trainRepository.save(train);
    }

    @Override
    public void findByCompany(Company company) {
        trainRepository.findByCompany(company);
    }
}
