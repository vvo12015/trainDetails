package net.vrakin.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="route_id")
    private Route route;*/

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="train_id")
    private Train train;
/*
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cargo_id")
    private Cargo cargo;
*/
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="state_id")
    private OrderState state;

    @Column(name="car_count")
    private Integer carCount;

    @Column(name="full_wear")
    private Integer fullWear;

    @Column(name="profit")
    private Integer profit;

    @Column(name="creation_date", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date creationDate;

    @Column(name="waiting_deadline")
    private Date waitingDeadline;

    @Column(name="deadline1")
    private Date deadline1;

    @Column(name="deadline2")
    private Date deadline2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }*/

    public Integer getCarCount() {
        return carCount;
    }

    public void setCarCount(Integer carCount) {
        this.carCount = carCount;
    }

    public Integer getFullWear() {
        return fullWear;
    }

    public void setFullWear(Integer fullWear) {
        this.fullWear = fullWear;
    }

    public Integer getProfit() {
        return profit;
    }

    public void setProfit(Integer profit) {
        this.profit = profit;
    }

    public Date getWaitingDeadline() {
        return waitingDeadline;
    }

    public void setWaitingDeadline(Date waitingDeadline) {
        this.waitingDeadline = waitingDeadline;
    }

    public Date getDeadline1() {
        return deadline1;
    }

    public void setDeadline1(Date deadline1) {
        this.deadline1 = deadline1;
    }

    public Date getDeadline2() {
        return deadline2;
    }

    public void setDeadline2(Date deadline2) {
        this.deadline2 = deadline2;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /*public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }
*/

    public Order(Long id, Train train, Integer carCount, Integer fullWear, Integer profit, Date creationDate, Date waitingDeadline, Date deadline1, Date deadline2) {
        this.id = id;
        this.train = train;
        this.carCount = carCount;
        this.fullWear = fullWear;
        this.profit = profit;
        this.creationDate = creationDate;
        this.waitingDeadline = waitingDeadline;
        this.deadline1 = deadline1;
        this.deadline2 = deadline2;
    }

    public Order(Train train, Integer carCount, Integer fullWear, Integer profit, Date creationDate, Date waitingDeadline, Date deadline1, Date deadline2) {
        this.train = train;
        this.carCount = carCount;
        this.fullWear = fullWear;
        this.profit = profit;
        this.creationDate = creationDate;
        this.waitingDeadline = waitingDeadline;
        this.deadline1 = deadline1;
        this.deadline2 = deadline2;
    }

    public Order() {
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(train, order.train) &&
                Objects.equals(carCount, order.carCount) &&
                Objects.equals(fullWear, order.fullWear) &&
                Objects.equals(profit, order.profit) &&
                Objects.equals(creationDate, order.creationDate) &&
                Objects.equals(waitingDeadline, order.waitingDeadline) &&
                Objects.equals(deadline1, order.deadline1) &&
                Objects.equals(deadline2, order.deadline2);
    }

    @Override
    public int hashCode() {

        return Objects.hash(train, carCount, fullWear, profit, creationDate, waitingDeadline, deadline1, deadline2);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", train=" + train +
                ", carCount=" + carCount +
                ", fullWear=" + fullWear +
                ", profit=" + profit +
                ", creationDate=" + creationDate +
                ", waitingDeadline=" + waitingDeadline +
                ", deadline1=" + deadline1 +
                ", deadline2=" + deadline2 +
                '}';
    }
}
