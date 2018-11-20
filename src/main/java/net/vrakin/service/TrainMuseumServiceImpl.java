package net.vrakin.service;

import net.vrakin.model.TrainMuseum;
import net.vrakin.repository.TrainMuseumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public TrainMuseum findById(Long id) {
        return trainMuseumRepo.findById(id).get();
    }

    @Override
    public void delete(TrainMuseum trainMuseum) {
        trainMuseumRepo.delete(trainMuseum);
    }

    @Override
    public TrainMuseum findFirst() {
        return trainMuseumRepo.findAll().get(0);
    }

    @Override
    public List<Map<String, String>> findAllToMap() {
        return findAll().stream().map(TrainMuseum::toMap).collect(Collectors.toList());
    }
}
