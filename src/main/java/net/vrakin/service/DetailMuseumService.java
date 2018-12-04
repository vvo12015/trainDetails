package net.vrakin.service;

import net.vrakin.model.DetailMuseum;
import net.vrakin.model.TrainMuseum;

import java.util.List;
import java.util.Map;

public interface DetailMuseumService extends GeneralService<DetailMuseum> {

    DetailMuseum findByName(String name);

    List<Map<String, Object>> findAllWithButton(TrainMuseum trainMuseum);

    List<Map<String, Object>> findOfTrainWithButton(Long train_id);
}
