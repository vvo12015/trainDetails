package net.vrakin.service;

import lombok.extern.slf4j.Slf4j;
import net.vrakin.model.ShowContentsInList;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public abstract class GeneralAbstractService<T extends ShowContentsInList> implements GeneralService<T> {

    protected JpaRepository<T, Long> repo;

    @PostConstruct
    protected abstract void init();

    @Override
    public List<T> findAll() {
        log.debug("call method: findAll");

        return repo.findAll();
    }

    @Override
    public List<Map<String, Object>> findAllToMap() {
        log.debug("call method: findAllToMap");

        return repo.findAll().stream().map(ShowContentsInList::toMap).collect(Collectors.toList());
    }

    @Override
    public T findById(Long id) {
        log.debug("call method: findById");

        if (repo.findById(id).isPresent()){
            T t = repo.findById(id).get();
            log.debug("findById. Id: " + id + ". Found " + t.getName());
            return t;
        }
        log.debug("findById. Id: " + id + ". No found ");

        return null;
    }

    @Override
    public void save(T object) {
        log.debug("call method: save with t-object: " + object.getName());
        repo.save(object);
    }

    @Override
    public void delete(T object) {
        log.debug("call method: delete with t-object: " + object.getName());
        repo.delete(object);
    }
}
