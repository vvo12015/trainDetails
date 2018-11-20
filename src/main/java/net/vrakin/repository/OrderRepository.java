package net.vrakin.repository;

import net.vrakin.model.Order;
import net.vrakin.model.OrderState;
import net.vrakin.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByTrain(Train train);

    List<Order> findByTrainIn(List<Train> trains);

    List<Order> findByWaitingDeadlineAfter(Date date);

    List<Order> findByWaitingDeadlineBefore(Date date);

    List<Order> findByTrainAndState(Train train, OrderState state);

    List<Order> findByTrainAndStateIn(Train train, List<OrderState> state);

    @Procedure
    public boolean refresh_orders();

    @Procedure
    public boolean genareting_orders();
}
