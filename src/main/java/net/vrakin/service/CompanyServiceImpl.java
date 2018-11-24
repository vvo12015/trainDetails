package net.vrakin.service;

import net.vrakin.model.Company;
import net.vrakin.model.User;
import net.vrakin.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CityService cityService;

    @Override
    public void save(Company company) {
        companyRepository.save(company);
    }

    @Override
    public List<Company> findByUser(User user) {
        return companyRepository.findByUser(user);
    }

    @Override
    public void registrationCompany(User user) {

        Company company = new Company(
                "MyCompany",
                15000F,
                user,
                cityService.findById(1L)
        );

        companyRepository.save(company);
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company findById(Long id) {
        return companyRepository.findById(id).get();
    }

    @Override
    public Company findByUserAndName(User user, String name) {
        return companyRepository.findByUserAndName(user, name).get();
    }

    @Override
    public void delete(Company object) {
        companyRepository.delete(object);
    }

    @Override
    public List<Map<String, String>> findAllToMap() {
        return findAll().stream().map(Company::toMap).collect(Collectors.toList());
    }

    @Override
    public boolean checkUniqueName(String name) {
        return false;
    }

    public boolean checkUniqueName(User user, String name) {
        return companyRepository.findByUserAndName(user, name).isPresent();
    }
}
