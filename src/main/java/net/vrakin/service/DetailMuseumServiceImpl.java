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
public class DetailMuseumServiceImpl implements DetailMuseumService {

    @Autowired
    private DetailsMuseumRepository detailsMuseumRepository;

    @Override
    public List<DetailMuseum> findAll() {
        return detailsMuseumRepository.findAll();
    }

    @Override
    public DetailMuseum findById(Long id) {
        return detailsMuseumRepository.findById(id).get();
    }

    @Override
    public void save(DetailMuseum object) {
        detailsMuseumRepository.save(object);
    }

    @Override
    public void delete(DetailMuseum object) {
        detailsMuseumRepository.delete(object);
    }

    @Override
    public List<Map<String, String>> findAllToMap() {
        return findAll().stream().map(DetailMuseum::toMap).collect(Collectors.toList());
    }

    @Override
    public boolean checkUniqueName(String name) {
        return false;
    }
}
