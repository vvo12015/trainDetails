package net.vrakin.service;

import net.vrakin.model.Company;
import net.vrakin.model.User;

import java.util.List;

public interface CompanyService extends GeneralService<Company>{

    List<Company> findByUser(User user);
    void registrationCompany(User user);
    List<Company> findByName(String name);
}
