package net.vrakin.service;

import net.vrakin.model.TrainMuseum;
import net.vrakin.repository.TrainMuseumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class TrainMuseumServiceImpl implements TrainMuseumService {

    @Autowired
    private TrainMuseumRepository trainMuseumRepo;

    @Autowired
    private EntityManager entityManager;

    public TrainMuseumServiceImpl(TrainMuseumRepository trainMuseumRepo) {
        this.trainMuseumRepo = trainMuseumRepo;
    }

    public TrainMuseumServiceImpl() {
    }


    @Override
    public void saveOrUpdate(TrainMuseum trainMuseum) {
        entityManager.merge(trainMuseum);
    }

    @Override
    public List<TrainMuseum> findAll() {
        return (List<TrainMuseum>) trainMuseumRepo.findAll();
    }

    @Override
    public Optional<TrainMuseum> load(Long id) {
        return trainMuseumRepo.findById(id);
    }

    @Override
    public void delete(TrainMuseum trainMuseum) {
        trainMuseumRepo.delete(trainMuseum);
    }
}
