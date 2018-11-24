package net.vrakin.service;

import net.vrakin.model.ShowContentsInList;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class GeneralAbstractService<T extends ShowContentsInList> implements GeneralService<T> {

    protected JpaRepository<T, Long> repo;

    @PostConstruct
    protected abstract void init();

    @Override
    public List<T> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Map<String, String>> findAllToMap() {
        return repo.findAll().stream().map(ShowContentsInList::toMap).collect(Collectors.toList());
    }

    @Override
    public T findById(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public void save(T object) {
        repo.save(object);
    }

    @Override
    public void delete(T object) {
        repo.delete(object);
    }
}
