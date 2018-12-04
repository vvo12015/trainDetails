package net.vrakin.service;

import net.vrakin.model.Detail;
import net.vrakin.model.ShowContentsInList;
import net.vrakin.model.Train;
import net.vrakin.model.TrainMuseum;
import net.vrakin.repository.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DetailServiceImpl extends GeneralAbstractService<Detail> implements DetailService {

    @Autowired
    private DetailRepository detailRepository;

    @Autowired
    private TrainMuseumService trainMuseumService;

    @Autowired
    private TrainService trainService;

    @Override
    protected void init() {
        this.repo = detailRepository;
    }

    @Override
    public boolean checkUniqueName(String name) {
        return detailRepository.findByName(name).isPresent();
    }

    @Override
    public Detail findByName(String name) {
        return detailRepository.findByName(name).get();
    }

    @Override
    public void buyDetails(Train train) {
        train.getTrainMuseum().getDetails().forEach(d_m->{
            Detail detail = new Detail(train, d_m);
            save(detail);
        });
    }

    @Override
    public List<Map<String, Object>> findByTrain(Long train_id) {

        Train train = trainService.findById(train_id);

        return detailRepository.findByTrain(train).stream()
                .map(ShowContentsInList::toMap)
                .collect(Collectors.toList());
    }

    @Override
    public List<Map<String, Object>> findAllWithButton(Long train_id) {

        Train train = trainService.findById(train_id);

        return detailRepository.findByTrain(train).stream().map(d->{
            Map<String, Object> result = d.toMap();
            List<String> buttons = new ArrayList<>();
            String button;
            button = "sell_" + train.getId();
            buttons.add(button);
            result.put("buttons", buttons);
            return result;
        }).collect(Collectors.toList());
    }
}
