package net.vrakin.repository;

import net.vrakin.model.Order;
import net.vrakin.model.OrderState;
import net.vrakin.model.Train;
import net.vrakin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
    boolean refresh_orders();

    @Procedure
    boolean start_order(Long order_id, Long train_id);

    @Query(value = "select * from Orders o where o.train_id = ?1 and o.state_id between 2 and 5", nativeQuery = true)
    List<Order> findNotGenerationOrders(Long train_id);
}
