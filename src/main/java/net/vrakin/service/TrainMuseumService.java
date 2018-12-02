package net.vrakin.service;

import net.vrakin.model.DetailMuseum;
import net.vrakin.model.TrainMuseum;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TrainMuseumService extends GeneralService<TrainMuseum>{

    List<TrainMuseum> findByDetails(List<DetailMuseum> details);
}
