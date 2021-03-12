package net.vrakin.service;

import lombok.extern.slf4j.Slf4j;
import net.vrakin.exception.CompanyNotFoundException;
import net.vrakin.model.City;
import net.vrakin.model.Company;
import net.vrakin.model.User;
import net.vrakin.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CompanyServiceImpl extends GeneralAbstractService<Company> implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CityService cityService;

    @Override
    protected void init() {
        this.repo = companyRepository;
        log.debug("call method: init");
    }

    @Override
    public List<Company> findByUser(User user) throws CompanyNotFoundException {
        List<Company> companies = companyRepository.findByUser(user);
        if (companies.size() > 0) {
            log.debug("call method: findByUser user: " + user.getUsername() + ". Found - " + companies.size() + " companies");
            return companies;
        }
        throw new CompanyNotFoundException("Company not found with username: " + user.getUsername());
    }

    @Override
    public void registrationCompany(User user) {
        log.debug("call method: registrationCompany with user: " + user.getUsername());

        Company company = new Company(
                "MyCompany",
                15000F,
                user,
                cityService.findById(1L)
        );

        companyRepository.save(company);
        log.debug("registrationCompany " + company.getName() + " successfully. Company has $" + company.getCash());
    }

    @Override
    public Company findByUserAndName(User user, String name) throws CompanyNotFoundException {

        if (companyRepository.findByUserAndName(user, name).isPresent()){
            Company company = companyRepository.findByUserAndName(user, name).get();
            log.debug("call method: findByName with name: " + name + ". Found - " + company.getName());
        }

        throw new CompanyNotFoundException("Company not found with username: " + user.getUsername());
    }

    @Override
    public List<Map<String, Object>> findAllToMap() {
        log.debug("call method: findAllToMap");

        return findAll().stream().map(Company::toMap).collect(Collectors.toList());
    }

    @Override
    public boolean checkUniqueName(String name) {
        log.debug("call method: checkUniqueName with name: " + name + "equals - " + false);
        return false;
    }

    public boolean checkUniqueName(User user, String name) {
        boolean b = companyRepository.findByUserAndName(user, name).isPresent();
        log.debug("call method: checkUniqueName with user: " + user.getUsername() + "and name: " + name + ". Value - " + b);

        return b;
    }
}
