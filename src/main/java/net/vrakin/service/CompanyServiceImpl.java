package net.vrakin.service;

import net.vrakin.model.Company;
import net.vrakin.model.User;
import net.vrakin.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

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
                user
        );

        companyRepository.save(company);
    }
}
