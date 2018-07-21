package net.vrakin.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "train_museum")
public class TrainMuseum {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "power")
    private Integer power;

    @Column(name="speed")
    private Integer speed;

    @Column(name = "price")
    private Integer price;

    @Column(name = "mass")
    private Integer mass;

    @Column(name = "corps_price")
    private Integer corpsPrice;

    @Column(name = "reliability")
    private Byte reliability;

    @Column(name = "limit_age")
    private Byte limitAge;

    @Column(name = "corps_wear")
    private Byte corpsWear;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "train_detail_museum",
            joinColumns= @JoinColumn(name = "train_museum_id"),
            inverseJoinColumns  = @JoinColumn(name = "detail_id")
    )
    private List<TrainMuseum> details = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getMass() {
        return mass;
    }

    public void setMass(Integer mass) {
        this.mass = mass;
    }

    public Byte getReliability() {
        return reliability;
    }

    public void setReliability(Byte reliability) {
        this.reliability = reliability;
    }

    public Byte getLimitAge() {
        return limitAge;
    }

    public void setLimitAge(Byte limitAge) {
        this.limitAge = limitAge;
    }

    public Integer getCorpsPrice() {
        return corpsPrice;
    }

    public void setCorpsPrice(Integer corpsPrice) {
        this.corpsPrice = corpsPrice;
    }

    public Byte getCorpsWear() {
        return corpsWear;
    }

    public void setCorpsWear(Byte corpsWear) {
        this.corpsWear = corpsWear;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public List<TrainMuseum> getDetails() {
        return details;
    }

    public void setDetails(List<TrainMuseum> details) {
        this.details = details;
    }

    public TrainMuseum() {
    }

    public TrainMuseum(String name, String description, Integer power, Integer speed, Integer price, Integer mass, Integer corpsPrice, Byte reliability, Byte limitAge, Byte corpsWear, List<TrainMuseum> details) {
        this.name = name;
        this.description = description;
        this.power = power;
        this.speed = speed;
        this.price = price;
        this.mass = mass;
        this.corpsPrice = corpsPrice;
        this.reliability = reliability;
        this.limitAge = limitAge;
        this.corpsWear = corpsWear;
        this.details = details;
    }

    public TrainMuseum(Long id, String name, String description, Integer power, Integer speed, Integer price, Integer mass, Integer corpsPrice, Byte reliability, Byte limitAge, Byte corpsWear, List<TrainMuseum> details) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.power = power;
        this.speed = speed;
        this.price = price;
        this.mass = mass;
        this.corpsPrice = corpsPrice;
        this.reliability = reliability;
        this.limitAge = limitAge;
        this.corpsWear = corpsWear;
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainMuseum that = (TrainMuseum) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(power, that.power) &&
                Objects.equals(speed, that.speed) &&
                Objects.equals(price, that.price) &&
                Objects.equals(mass, that.mass) &&
                Objects.equals(corpsPrice, that.corpsPrice) &&
                Objects.equals(reliability, that.reliability) &&
                Objects.equals(limitAge, that.limitAge) &&
                Objects.equals(corpsWear, that.corpsWear) &&
                Objects.equals(details, that.details);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, description, power, speed, price, mass, corpsPrice, reliability, limitAge, corpsWear, details);
    }

    @Override
    public String toString() {
        return "TrainMuseum{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", power=" + power +
                ", speed=" + speed +
                ", price=" + price +
                ", mass=" + mass +
                ", corpsPrice=" + corpsPrice +
                ", reliability=" + reliability +
                ", limitAge=" + limitAge +
                ", corpsWear=" + corpsWear +
                ", details=" + details +
                '}';
    }
}
