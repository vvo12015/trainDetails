package net.vrakin.service;

import net.vrakin.model.TrainMuseum;
import net.vrakin.repository.TrainMuseumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainMuseumServiceImpl implements TrainMuseumService {

    @Autowired
    private TrainMuseumRepository trainMuseumRepo;

    public TrainMuseumServiceImpl(TrainMuseumRepository trainMuseumRepo) {
        this.trainMuseumRepo = trainMuseumRepo;
    }

    public TrainMuseumServiceImpl() {
    }


    @Override
    public void save(TrainMuseum trainMuseum) {
        trainMuseumRepo.save(trainMuseum);
    }

    @Override
    public List<TrainMuseum> findAll() {
        return (List<TrainMuseum>) trainMuseumRepo.findAll();
    }
}
