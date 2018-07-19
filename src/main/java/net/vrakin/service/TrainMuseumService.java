package net.vrakin.service;

import net.vrakin.model.TrainMuseum;

import java.util.List;
import java.util.Optional;

public interface TrainMuseumService {

    void saveOrUpdate(TrainMuseum trainMuseum);

    List<TrainMuseum> findAll();

    Optional<TrainMuseum> load(Long id);

    void delete(TrainMuseum trainMuseum);
}
