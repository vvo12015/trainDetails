package net.vrakin.repository;

import net.vrakin.model.Company;
import net.vrakin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

   List<Company> findByUser(User user);
}
