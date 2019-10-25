package net.vrakin.service;

import net.vrakin.model.*;
import net.vrakin.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainServiceImpl extends GeneralAbstractService<Train> implements TrainService {

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private TrainMuseumService trainMuseumService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CityService cityService;

    @Autowired
    private DetailService detailService;

    @Autowired
    private TrainStatusService trainStatusService;

    @Override
    protected void init() {
        this.repo = trainRepository;
    }

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

    public void trainBuy(@AuthenticationPrincipal User user, @PathVariable("id") Long trainMuseum_id) {
        TrainMuseum trainMuseum = trainMuseumService.findById(trainMuseum_id);
        Company company = companyService.findByUser(user).get(0);
        Long trainOfTypeCount = company.getTrains()
                .stream()
                .filter(train -> train.getTrainMuseum().equals(trainMuseum))
                .count();
        Train train = new Train(
                trainMuseum.getName() + trainOfTypeCount.toString(),
                company,
                trainMuseum
        );
        TrainStatus trainStatus = trainStatusService.findByName("PROGRESS");
        train.setCity(company.getCity()==null?cityService.findById(1L):company.getCity());
        train.setStatus(trainStatus);
        company.setCash(company.getCash() - trainMuseum.getPrice());
        companyService.save(company);
        save(train);

        detailService.buyDetails(train);
    }

    public void trainSell(@PathVariable("id") Long train_id) {
        Train train = findById(train_id);
        Company company = train.getCompany();

        Float cost = Float.valueOf(train.getCorpsState()) * train.getTrainMuseum().getPrice() / 100;
        company.setCash(company.getCash() + cost);
        companyService.save(company);

        save(train);

        detailService.buyDetails(train);
    }
}
