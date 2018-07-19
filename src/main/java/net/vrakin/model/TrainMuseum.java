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

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
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

    public TrainMuseum() {
    }

    public TrainMuseum(Long id, String name, String description, Integer power, Integer price,
                       Integer mass, Byte reliability, Byte limitAge, Integer corpsPrice, Byte corpsWear) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.power = power;
        this.price = price;
        this.mass = mass;
        this.reliability = reliability;
        this.limitAge = limitAge;
        this.corpsPrice = corpsPrice;
        this.corpsWear = corpsWear;
    }

    public TrainMuseum(String name, String description, Integer power, Integer price, Integer mass, Byte reliability, Byte limitAge, Integer corpsPrice, Byte corpsWear) {
        this.name = name;
        this.description = description;
        this.power = power;
        this.price = price;
        this.mass = mass;
        this.reliability = reliability;
        this.limitAge = limitAge;
        this.corpsPrice = corpsPrice;
        this.corpsWear = corpsWear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainMuseum that = (TrainMuseum) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(power, that.power) &&
                Objects.equals(price, that.price) &&
                Objects.equals(mass, that.mass) &&
                Objects.equals(reliability, that.reliability) &&
                Objects.equals(limitAge, that.limitAge) &&
                Objects.equals(corpsPrice, that.corpsPrice) &&
                Objects.equals(corpsWear, that.corpsWear);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, description, power, price, mass, reliability, limitAge, corpsPrice, corpsWear);
    }

    @Override
    public String toString() {
        return "TrainMuseum{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", power=" + power +
                ", price=" + price +
                ", mass=" + mass +
                ", reliability=" + reliability +
                ", limitAge=" + limitAge +
                ", corpsPrice=" + corpsPrice +
                ", corpsWear=" + corpsWear +
                '}';
    }
}
