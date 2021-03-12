package net.vrakin.service;

import lombok.extern.slf4j.Slf4j;
import net.vrakin.model.DetailMuseum;
import net.vrakin.model.TrainMuseum;
import net.vrakin.repository.TrainMuseumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TrainMuseumServiceImpl extends GeneralAbstractService<TrainMuseum> implements TrainMuseumService {

    @Autowired
    private TrainMuseumRepository trainMuseumRepo;

    @Override
    protected void init() {
        log.debug("call method: init");

        this.repo = trainMuseumRepo;
    }

    public TrainMuseumServiceImpl(TrainMuseumRepository trainMuseumRepo) {
        log.debug("call constructor: TrainMuseumServiceImpl with trainMuseumRepo");
        this.trainMuseumRepo = trainMuseumRepo;
    }

    public TrainMuseumServiceImpl() {
        log.debug("call empty constructor: TrainMuseumServiceImpl");
    }

    @Override
    public boolean checkUniqueName(String name) {
        boolean b = trainMuseumRepo.findByName(name).isPresent();
        log.debug("call method: checkUniqueName with name: " + name + ". Received value: " + b);
        return b;
    }

    @Override
    public List<TrainMuseum> findByDetails(List<DetailMuseum> details) {

        List<TrainMuseum> trainMuseums = trainMuseumRepo.findByDetails(details);

        log.debug("call method: findByDetails with detail count: " + details.size() + ". Received trainMuseum count: " + trainMuseums.size());

        return trainMuseums;
    }
}
