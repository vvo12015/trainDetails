package net.vrakin.service;

import lombok.extern.slf4j.Slf4j;
import net.vrakin.exception.CompanyNotFoundException;
import net.vrakin.exception.TrainNotFoundException;
import net.vrakin.model.*;
import net.vrakin.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
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

    @Override
    protected void init() {
        log.debug("call method: init");

        this.repo = trainRepository;
    }

    @Override
    public void deleteByCompany(Company company) {
        trainRepository.deleteByCompany(company);

        log.debug("call method: deleteByCompany with company: " + company.getName());
    }

    @Override
    public List<Train> findByCompany(Company company) {
        List<Train> trains = trainRepository.findByCompany(company);

        log.debug("call method: findByCompany with company: " + company.getName() +
                ". Received train count: " + trains.size());
        return trains;
    }

    @Override
    public List<Train> findByUser(User user) {
        List<Company> companies = null;
        List<Train> trains = new ArrayList<>();
        try {
            companies = companyService.findByUser(user);
            trains = trainRepository.findByCompanyIn(companies);
            log.debug("call method: findByUser with user: " + user.getUsername() + "Received train count: " + trains.size());
        } catch (CompanyNotFoundException e) {
            e.printStackTrace();
        }

        return trains;
    }

    @Override
    public List<Train> findByCompanyIn(List<Company> companies) {
        List<Train> trains = trainRepository.findByCompanyIn(companies);
        log.debug("call method: findByCompanyIn with company count: " + companies.size() + "Received train count: " + trains.size());
        return trainRepository.findByCompanyIn(companies);
    }

    @Override
    public Byte checkTechnicalStatus(Train train) {
        float currentState = train.getCorpsState() * 0.9F;
        log.debug("call method: checkTechnicalStatus with train: " + train.getName() + "Received value " + currentState);
        return (byte) currentState;
    }

    @Override
    public Train findByCompanyAndName(Company company, String name) throws TrainNotFoundException {

        Optional<Train> train = trainRepository.findByCompanyAndName(company, name);

        if (train.isPresent()){
            log.debug("call method: findByCompanyAndName with : company" + company.getName() +
                    "and name: " + name + ". Received value " + train.get().getName());

            return train.get();
        }
        throw new TrainNotFoundException("Not found train with company: " + company.getName() + "and name: " + name);
    }

    @Override
    public void delete(Train object) {
        super.delete(object);
    }

    public boolean checkUniqueName(Company company, String name) {
        boolean b = trainRepository.findByCompanyAndName(company, name).isPresent();
        log.debug("call method: checkUniqueName with name: " + name + ". Value - " + b);
        return b;
    }

    @Override
    public boolean checkUniqueName(String name) {
        return false;
    }

    private String newTrainNameFound(Company company, TrainMuseum trainMuseum){
        return "";
    }

    public void trainBuy(@AuthenticationPrincipal User user, @PathVariable("id") Long trainMuseum_id) {
        try {
            TrainMuseum trainMuseum = trainMuseumService.findById(trainMuseum_id);
            Company company = companyService.findByUser(user).get(0);
            long trainOfTypeCount = company.getTrains()
                    .stream()
                    .filter(train -> train.getTrainMuseum().equals(trainMuseum))
                    .count();
            Train train = new Train(
                    trainMuseum.getName() + Long.toString(trainOfTypeCount),
                    company,
                    trainMuseum
            );

            log.debug("call method: trainBuy with user " + user.getUsername() + " and trainMuseum: " + trainMuseum.getName());

            train.setCity(company.getCity() == null ? cityService.findById(1L) : company.getCity());
            company.setCash(company.getCash() - trainMuseum.getPrice());
            companyService.save(company);
            save(train);

            detailService.buyDetails(train);
            log.debug("save train " + train.getName() + ", detail count: " + train.getDetails().size());
        }catch (CompanyNotFoundException e){
            e.printStackTrace();
        }
    }
}
