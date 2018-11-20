package net.vrakin.repository;

import net.vrakin.model.City;
import net.vrakin.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    List<Route> findByCity1OrCity2(City city1, City city2);
}
