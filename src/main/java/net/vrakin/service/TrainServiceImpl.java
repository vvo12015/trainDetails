package net.vrakin.service;

import net.vrakin.model.Company;
import net.vrakin.model.Train;
import net.vrakin.model.User;
import net.vrakin.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private CompanyService companyService;

    @Override
    public void deleteByCompany(Company company) {
        trainRepository.deleteByCompany(company);
    }

    @Override
    public List<Train> findAll() {
        return trainRepository.findAll();
    }

    @Override
    public void save(Train train) {
        trainRepository.save(train);
    }

    @Override
    public List<Train> findByCompany(Company company) {
        return trainRepository.findByCompany(company);
    }

    @Override
    public Train findById(Long trainId) {
        return trainRepository.findById(trainId).get();
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
    public void delete(Train object) {
        trainRepository.delete(object);
    }

    @Override
    public List<Map<String, String>> findAllToMap() {
        return findAll().stream().map(Train::toMap).collect(Collectors.toList());
    }

    @Override
    public Byte checkTechnicalStatus(Train train) {
        Float currentState = train.getCorpsState() * 0.9F;

        return currentState.byteValue();
    }
}
