package net.vrakin.repository;

import net.vrakin.model.Company;
import net.vrakin.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrainRepository extends JpaRepository<Train, Long> {
    void deleteByCompany(Company company);
    List<Train> findByCompany(Company company);

    List<Train> findByCompanyIn(List<Company> companies);

    Optional<Train> findByName(String name);
}
