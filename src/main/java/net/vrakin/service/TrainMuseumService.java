package net.vrakin.service;

import net.vrakin.model.TrainMuseum;

import java.util.List;

public interface TrainMuseumService {

    void save(TrainMuseum trainMuseum);

    List<TrainMuseum> findAll();
}
