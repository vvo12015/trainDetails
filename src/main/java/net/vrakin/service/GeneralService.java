package net.vrakin.service;

import net.vrakin.model.ShowContentsInList;

import java.util.List;
import java.util.Map;

public interface GeneralService<T extends ShowContentsInList> {

    List<T> findAll();
    List<Map<String, Object>> findAllToMap();
    T findById(Long id);
    void save(T object);
    void delete(T object);

    boolean checkUniqueName(String name);
}
