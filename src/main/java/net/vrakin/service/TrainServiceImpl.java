package net.vrakin.service;

import net.vrakin.model.Company;
import net.vrakin.model.Train;
import net.vrakin.model.User;
import net.vrakin.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainServiceImpl extends GeneralAbstractService<Train> implements TrainService {

    @Autowired
    private TrainRepository trainRepository;

    @Override
    protected void init() {
        this.repo = trainRepository;
    }

    @Autowired
    private CompanyService companyService;

    @Override
    public void deleteByCompany(Company company) {
        trainRepository.deleteByCompany(company);
    }

    @Override
    public List<Train> findByCompany(Company company) {
        return trainRepository.findByCompany(company);
    }

    @Override
    public List<Train> findByUser(User user) {
        List<Company> companies = companyService.findByUser(user);

        return trainRepository.findByCompanyIn(companies);
    }

    @Override
    public List<Train> findByCompanyIn(List<Company> companies) {
        return trainRepository.findByCompanyIn(companies);
    }

    @Override
    public Byte checkTechnicalStatus(Train train) {
        Float currentState = train.getCorpsState() * 0.9F;

        return currentState.byteValue();
    }

    @Override
    public Train findByCompanyAndName(Company company, String name) {
        return trainRepository.findByCompanyAndName(company, name).get();
    }

    @Override
    public boolean checkUniqueName(String name) {
        return false;
    }
}
