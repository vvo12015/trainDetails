package net.vrakin.service;

import net.vrakin.model.City;
import net.vrakin.model.DetailMuseum;
import net.vrakin.repository.DetailsMuseumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DetailMuseumServiceImpl extends GeneralAbstractService<DetailMuseum> implements DetailMuseumService {

    @Autowired
    private DetailsMuseumRepository detailsMuseumRepository;

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
}
