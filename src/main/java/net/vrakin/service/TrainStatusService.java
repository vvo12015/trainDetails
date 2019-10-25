package net.vrakin.service;

import net.vrakin.model.TrainStatus;

public interface TrainStatusService extends GeneralService<TrainStatus>{

    TrainStatus findByName(String name);
}
