package net.vrakin.service;

import net.vrakin.model.Detail;
import net.vrakin.model.DetailMuseum;
import net.vrakin.model.Train;
import net.vrakin.model.TrainMuseum;

import java.util.List;
import java.util.Map;

public interface DetailService extends GeneralService<Detail> {

    Detail findByName(String name);

    List<Map<String, Object>> findAllWithButton(Long train_id);

    void buyDetails(Train train);

    List<Map<String, Object>> findByTrain(Long train_id);

}
