package net.vrakin.service;

import net.vrakin.model.Company;
import net.vrakin.model.Train;
import net.vrakin.model.User;

import java.util.List;
import java.util.Optional;

public interface TrainService extends GeneralService<Train>{

    void deleteByCompany(Company company);

    List<Train> findByCompany(Company company);

    List<Train> findByCompanyIn(List<Company> companies);

    List<Train> findByUser(User user);

    Byte checkTechnicalStatus(Train train);

    Train findByCompanyAndName(Company company, String name);

    void trainBuy(User user, Long trainMuseumId);

    void trainSell(Long trainId);
}
