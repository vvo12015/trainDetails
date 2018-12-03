package net.vrakin.repository;

import net.vrakin.model.Company;
import net.vrakin.model.Train;
import net.vrakin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {
    void deleteByCompany(Company company);
    List<Train> findByCompany(Company company);

    List<Train> findByCompanyIn(List<Company> companies);

    Optional<Train> findByCompanyAndName(Company company, String name);
}
