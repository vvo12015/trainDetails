package net.vrakin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "train_museum")
@Data
@NoArgsConstructor
public class TrainMuseum implements ShowContentsInList{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @EqualsAndHashCode.Exclude
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
    private List<DetailMuseum> details = new ArrayList<>();

    public TrainMuseum(String name, String description, Integer power, Integer speed,
                       Integer price, Integer mass, Integer corpsPrice, Byte reliability,
                       Byte limitAge, Byte corpsWear, List<DetailMuseum> details) {
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

    public TrainMuseum(Long id, String name, String description, Integer power, Integer speed,
                       Integer price, Integer mass, Integer corpsPrice, Byte reliability, Byte limitAge,
                       Byte corpsWear, List<DetailMuseum> details) {
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
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
                map.put("id", id.toString());
                map.put("name", name);
                map.put("description", description);
                map.put("power", power.toString());
                map.put("speed", speed.toString());
                map.put("price", price.toString());
                map.put("mass", mass.toString());
                map.put("corpsPrice", corpsPrice.toString());
                map.put("reliability", reliability.toString());
                map.put("limitAge", limitAge.toString());
                map.put("corpsWear", corpsWear.toString());
                List<String> buttons = new ArrayList<>();

                buttons.add("buy");
                buttons.add("details");

                map.put("buttons", buttons);
        return map;
    }

    public static List<String> getFields() {

        List<String> fields = new ArrayList<>();

        fields.add("id");
        fields.add("name");
        fields.add("description");
        fields.add("power");
        fields.add("speed");
        fields.add("price");
        fields.add("mass");
        fields.add("corpsPrice");
        fields.add("reliability");
        fields.add("limitAge");
        fields.add("corpsWear");

        return fields;
    }
}
