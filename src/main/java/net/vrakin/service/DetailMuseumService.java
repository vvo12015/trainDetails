package net.vrakin.service;

import net.vrakin.model.DetailMuseum;

public interface DetailMuseumService extends GeneralService<DetailMuseum> {

    DetailMuseum findByName(String name);
}
