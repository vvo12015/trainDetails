package net.vrakin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    @ToString.Include
    private Long id;

    @Column(name = "name")
    @ToString.Include
    private String name;

    @Column(name = "description")
    @ToString.Include
    private String description;

    @Column(name = "power")
    @ToString.Include
    private Integer power;

    @Column(name="speed")
    @ToString.Include
    private Integer speed;

    @Column(name = "price")
    @ToString.Include
    private Integer price;

    @Column(name = "mass")
    @ToString.Include
    private Integer mass;

    @Column(name = "corps_price")
    @ToString.Include
    private Integer corpsPrice;

    @Column(name = "reliability")
    @ToString.Include
    private Byte reliability;

    @Column(name = "limit_age")
    @ToString.Include
    private Byte limitAge;

    @Column(name = "corps_wear")
    @ToString.Include
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
