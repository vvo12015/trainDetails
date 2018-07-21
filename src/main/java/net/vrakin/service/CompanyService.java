package net.vrakin.service;

import net.vrakin.model.Company;
import net.vrakin.model.User;

import java.util.List;

public interface CompanyService {

    List<Company> findByUser(User user);
    void save(Company company);
    void registrationCompany(User user);

}
