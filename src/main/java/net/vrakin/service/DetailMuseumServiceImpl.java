package net.vrakin.service;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class DetailMuseumServiceImpl extends GeneralAbstractService<DetailMuseum> implements DetailMuseumService {

    @Autowired
    private DetailsMuseumRepository detailsMuseumRepository;

    @Autowired
    private TrainMuseumService trainMuseumService;

    @Autowired
    private TrainService trainService;

    @Override
    protected void init() {
        log.debug("call method: init");

        this.repo = detailsMuseumRepository;
    }

    @Override
    public boolean checkUniqueName(String name) {
        boolean b = detailsMuseumRepository.findByName(name).isPresent();

        log.debug("call method: checkUniqueName with name:" + name + ". Value - " + b);
        return b;
    }

    @Override
    public DetailMuseum findByName(String name) {
        log.debug("call method: findByName");
        if (detailsMuseumRepository.findByName(name).isPresent()){
            DetailMuseum detailMuseum = detailsMuseumRepository.findByName(name).get();
            log.debug("findByName of detailMuseum with name: " + detailMuseum.getName() + " successfully");
            return detailMuseum;
        }
        log.debug("findByName of detailMuseum with name: " + name + "not found");

        return null;
    }

    @Override
    public List<Map<String, Object>> findAllWithButton(TrainMuseum trainMuseum) {
        log.debug("call method findAllWithButton with trainMuseum: " + trainMuseum.getName());
        StringBuilder logs = new StringBuilder("list button");
        List<Map<String, Object>> results = findAll().stream().map(d->{
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
            logs.append(button + "; ");
            result.put("buttons", buttons);
            return result;
        }).collect(Collectors.toList());

        log.debug("added buttons: " + logs.toString() + " successfully");
        return results;
    }

    @Override
    public List<Map<String, Object>> findOfTrainWithButton(Long train_id) {
        log.debug("call method: findOfTrainWithButton with train_id: " + train_id);
        Train train = trainService.findById(train_id);

        List<DetailMuseum> detailMuseumList = train.getDetails().stream()
                .map(Detail::getDetailMuseum)
                .collect(Collectors.toList());

        log.debug("formation list of detailsMuseum size: " + detailMuseumList.size() + " successfully");

        StringBuilder logs = new StringBuilder("list of button: ");

        List<Map<String, Object>> results = findAll().stream().map(d->{
            Map<String, Object> result = d.toMap();
            List<String> buttons = new ArrayList<>();
            String button;
            button = "buy_" + train_id;
            buttons.add(button);
            logs.append(button + "; ");
            result.put("buttons", buttons);
            return result;
        })
                .filter(d->!detailMuseumList.contains(d))
                .collect(Collectors.toList());

        log.debug("added buttons: " + logs.toString() + " successfully");

        return results;
    }
}
