package net.vrakin.service;

import net.vrakin.exception.CompanyNotFoundException;
import net.vrakin.model.Company;
import net.vrakin.model.User;

import java.util.List;
import java.util.Optional;

public interface CompanyService extends GeneralService<Company>{

    List<Company> findByUser(User user) throws CompanyNotFoundException;
    void registrationCompany(User user);
    Company findByUserAndName(User user, String name) throws CompanyNotFoundException;
}
