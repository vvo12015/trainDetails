package net.vrakin.service;

import net.vrakin.model.TrainMuseum;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TrainMuseumService {

    void saveOrUpdate(TrainMuseum trainMuseum);

    List<TrainMuseum> findAll();

    TrainMuseum load(Long id);

    void delete(TrainMuseum trainMuseum);

    TrainMuseum findFirst();
}
