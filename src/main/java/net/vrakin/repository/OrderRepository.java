package net.vrakin.repository;

import net.vrakin.model.Order;
import net.vrakin.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByTrain(Train train);

    List<Order> findByWaitingDeadlineAfter(Date date);

    List<Order> findByWaitingDeadlineBefore(Date date);

    Date currentDate();
}
