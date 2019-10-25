package net.vrakin.service;

import net.vrakin.model.TrainStatus;
import net.vrakin.repository.TrainStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TrainStatusServiceImpl extends GeneralAbstractService<TrainStatus> implements  TrainStatusService {

    @Autowired
    private TrainStatusRepository trainStatusRepository;

    @Override
    protected void init() {
        this.repo = trainStatusRepository;
    }

    @Override
    public TrainStatus findByName(String name) {
        return trainStatusRepository.findByName(name).get();
    }

    @Override
    public boolean checkUniqueName(String name) {
        return trainStatusRepository.findByName(name).isPresent();
    }
}
