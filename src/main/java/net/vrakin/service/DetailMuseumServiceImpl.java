package net.vrakin.service;

import net.vrakin.model.DetailMuseum;
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
}
