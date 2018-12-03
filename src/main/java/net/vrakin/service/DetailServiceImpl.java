package net.vrakin.service;

import net.vrakin.model.Detail;
import net.vrakin.model.Train;
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
    public List<Map<String, Object>> findAllWithButton(Train train) {

        return findAll().stream().map(d->{
            Map<String, Object> result = d.toMap();
            List<String> buttons = new ArrayList<>();
            String button;
            if (train.getDetails().contains(d)){
                button = "remove_" + train.getId();
            }
            else {
                button = "add_" + train.getId();
            }
            buttons.add(button);
            result.put("buttons", buttons);
            return result;
        }).collect(Collectors.toList());
    }
}
