package net.vrakin.service;

import net.vrakin.model.Detail;
import net.vrakin.model.DetailMuseum;
import net.vrakin.model.Train;
import net.vrakin.model.TrainMuseum;
import net.vrakin.repository.DetailsMuseumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DetailMuseumServiceImpl extends GeneralAbstractService<DetailMuseum> implements DetailMuseumService {

    @Autowired
    private DetailsMuseumRepository detailsMuseumRepository;

    @Autowired
    private TrainMuseumService trainMuseumService;

    @Autowired
    private TrainService trainService;

    @Override
    protected void init() {
        this.repo = detailsMuseumRepository;
    }

    @Override
    public boolean checkUniqueName(String name) {
        return detailsMuseumRepository.findByName(name).isPresent();
    }

    @Override
    public DetailMuseum findByName(String name) {
        return detailsMuseumRepository.findByName(name).get();
    }

    @Override
    public List<Map<String, Object>> findAllWithButton(TrainMuseum trainMuseum) {

        return findAll().stream().map(d->{
            Map<String, Object> result = d.toMap();
            List<String> buttons = new ArrayList<>();
            String button;
            if (trainMuseum.getDetails().contains(d)){
                button = "remove_" + trainMuseum.getId();
            }
            else {
                button = "add_" + trainMuseum.getId();
            }
            buttons.add(button);
            result.put("buttons", buttons);
            return result;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Map<String, Object>> findOfTrainWithButton(Long train_id) {
        Train train = trainService.findById(train_id);

        List<DetailMuseum> detailMuseumList = train.getDetails().stream()
                .map(Detail::getDetailMuseum)
                .collect(Collectors.toList());

        return findAll().stream().map(d->{
            Map<String, Object> result = d.toMap();
            List<String> buttons = new ArrayList<>();
            String button;
            button = "buy_" + train_id;
            buttons.add(button);
            result.put("buttons", buttons);
            return result;
        })
                .filter(d->!detailMuseumList.contains(d))
                .collect(Collectors.toList());
    }
}
