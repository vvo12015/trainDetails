package net.vrakin.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name="orders")
public class Order implements ShowContentsInList{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="route_id")
    protected Route route;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="train_id")
    protected Train train;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cargo_id")
    protected Cargo cargo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="state_id")
    protected OrderState state;

    @Column(name="car_count")
    protected Integer carCount;

    @Column(name="full_wear")
    protected Integer fullWear;

    @Column(name="profit")
    protected Integer profit;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Calendar creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "active_date")
    private Calendar activeDate;

    @Column(name="waiting_deadline")
    protected Long waitingDeadline;

    @Column(name="deadline1")
    protected Long deadline1;

    @Column(name="deadline2")
    protected Long deadline2;

    @Column(name = "execution")
    protected Integer execution;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Route getRoute() {
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
    }

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

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public Long getWaitingDeadline() {
        return waitingDeadline;
    }

    public void setWaitingDeadline(Long waitingDeadline) {
        this.waitingDeadline = waitingDeadline;
    }

    public Long getDeadline1() {
        return deadline1;
    }

    public void setDeadline1(Long deadline1) {
        this.deadline1 = deadline1;
    }

    public Long getDeadline2() {
        return deadline2;
    }

    public void setDeadline2(Long deadline2) {
        this.deadline2 = deadline2;
    }

    public Integer getExecution() {
        return execution;
    }

    public void setExecution(Integer execution) {
        this.execution = execution;
    }

    protected Integer getDistance() {
        return route.getDistance();
    }

    public Order() {
    }

    public Order getOrder(Order order) {

        order.setTrain(this.train);
        order.setCarCount(this.carCount);
        order.setCargo(this.cargo);
        order.setRoute(this.route);
        order.setDeadline1(this.deadline1);
        order.setDeadline2(this.deadline2);
        order.setWaitingDeadline(this.waitingDeadline);
        order.setState(this.state);
        order.setFullWear(this.fullWear);
        order.setProfit(this.profit);
        order.setExecution(this.execution);

        return order;
    }
    public Order(Long id, Route route, Train train, Cargo cargo, OrderState state, Integer carCount, Integer fullWear,
                 Integer profit, Calendar creationDate, Long waitingDeadline, Long deadline1, Long deadline2) {
        this.id = id;
        this.route = route;
        this.train = train;
        this.cargo = cargo;
        this.state = state;
        this.carCount = carCount;
        this.fullWear = fullWear;
        this.profit = profit;
        this.creationDate = creationDate;
        this.waitingDeadline = waitingDeadline;
        this.deadline1 = deadline1;
        this.deadline2 = deadline2;
        this.execution = 0;
    }

    public void setOrder(Order order) {
        this.id = order.getId();
        this.route = order.getRoute();
        this.train = order.getTrain();
        this.cargo = order.getCargo();
        this.state = order.getState();
        this.carCount = order.getCarCount();
        this.fullWear = order.getFullWear();
        this.profit = order.getProfit();
        this.creationDate = order.getCreationDate();
        this.waitingDeadline = order.getWaitingDeadline();
        this.deadline1 = order.getDeadline1();
        this.deadline2 = order.getDeadline2();
        this.execution = order.getExecution();
    }

    public Order(Route route, Train train, Cargo cargo, OrderState state, Integer carCount, Integer fullWear,
                 Integer profit, Calendar creationDate, Long waitingDeadline, Long deadline1, Long deadline2) {
        this.route = route;
        this.train = train;
        this.cargo = cargo;
        this.state = state;
        this.carCount = carCount;
        this.fullWear = fullWear;
        this.profit = profit;
        this.creationDate = creationDate;
        this.waitingDeadline = waitingDeadline;
        this.deadline1 = deadline1;
        this.deadline2 = deadline2;
        this.execution = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(route, order.route) &&
                Objects.equals(train, order.train) &&
                Objects.equals(cargo, order.cargo) &&
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

        return Objects.hash(route, train, cargo, carCount, fullWear, profit, creationDate,
                waitingDeadline, deadline1, deadline2);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", route=" + route +
                ", train=" + train +
                ", cargo=" + cargo +
                ", carCount=" + carCount +
                ", fullWear=" + fullWear +
                ", profit=" + profit +
                ", creationDate=" + creationDate.getTime().toString() +
                ", waitingDeadline=" + waitingDeadline +
                ", deadline1=" + deadline1 +
                ", deadline2=" + deadline2 +
                '}';
    }

    @Override
    public Map<String, Object> toMap() {
        
        Map<String, Object> map = new HashMap<>();

        map.put("id", id.toString());
        map.put("route", route.toString());
        map.put("name", route.toString() + ", " + cargo.toString() + "x" + carCount);
        map.put("train", train.getName());
        map.put("cargo", cargo.getName());
        map.put("state", state.getName());
        map.put("carCount", carCount.toString());
        map.put("fullWear", fullWear.toString());
        map.put("profit", profit.toString());
        map.put("waitingDeadline", waitingDeadline.toString());
        map.put("deadline1", deadline1.toString());
        map.put("deadline2", deadline2.toString());
        map.put("execution", execution.toString());

        List<String> buttons = new ArrayList<>();
        String buttonName = "";
        switch (state.getId().intValue()){
            case 1:
                buttonName = "start";
                break;
            case 2: case 3: case 4:
                buttonName = "progress";
                break;
            case 5: case 6:
                buttonName = "finish";
                break;
        }

        buttons.add(buttonName);
        map.put("buttons", buttons);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yy");
        map.put("creationDate", simpleDateFormat.format(creationDate.getTime()));
        if (activeDate != null) map.put("activeDate", simpleDateFormat.format(activeDate.getTime()));
        else map.put("activeDate", "-");

        return map;
    }

    public static List<String> getFields() {

        List<String> fields = new ArrayList<>();

        fields.add("id");
        fields.add("train");
        fields.add("activeDate");
        fields.add("creationDate");
        fields.add("state");
        fields.add("execution");
        fields.add("profit");
        fields.add("deadline1");
        fields.add("deadline2");
        fields.add("waitingDeadline");
        fields.add("carCount");
        fields.add("cargo");
        fields.add("fullWear");
        fields.add("name");
        fields.add("route");

        return fields;
    }


}
