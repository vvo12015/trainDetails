package net.vrakin.service;

import net.vrakin.model.TrainMuseum;
import net.vrakin.repository.TrainMuseumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TrainMuseumServiceImpl extends GeneralAbstractService<TrainMuseum> implements TrainMuseumService {

    @Autowired
    private TrainMuseumRepository trainMuseumRepo;

    @Override
    protected void init() {
        this.repo = trainMuseumRepo;
    }

    public TrainMuseumServiceImpl(TrainMuseumRepository trainMuseumRepo) {
        this.trainMuseumRepo = trainMuseumRepo;
    }

    public TrainMuseumServiceImpl() {
    }

    @Override
    public boolean checkUniqueName(String name) {
        return trainMuseumRepo.findByName(name).isPresent();
    }
}
