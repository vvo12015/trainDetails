package net.vrakin.repository;

import net.vrakin.model.Company;
import net.vrakin.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, Long> {
    void deleteByCompany(Company company);
    void findByCompany(Company company);
}
