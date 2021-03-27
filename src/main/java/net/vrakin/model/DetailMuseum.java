package net.vrakin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;

@Entity
@Table(name="detail_museum")
@Data
@NoArgsConstructor
public class DetailMuseum implements ShowContentsInList{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    @ToString.Include
    private Long id;

    @Column(name="name")
    @ToString.Include
    private String name;

    @Column(name="wear")
    @ToString.Include
    private Byte wear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_ref")
    @ToString.Include
    private DetailMuseumType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_ref")
    @ToString.Include
    private DetailMuseumGroup group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_level_ref")
    @ToString.Include
    private GameLevel gameLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_type_ref")
    @ToString.Include
    private CityType cityType;

    @Column(name="is_repaired")
    @ToString.Include
    private Boolean isRepaired;

    @Column(name = "mass")
    @ToString.Include
    private Integer mass;

    @Column(name = "start_reliability")
    @ToString.Include
    private Integer start_reliability;

    @Column(name = "power")
    @ToString.Include
    private Integer power;

    @Column(name = "speed")
    @ToString.Include
    private Integer speed;

    @Column(name = "max_mass")
    @ToString.Include
    private Integer maxMass;

    @Column(name = "start_date_production")
    @ToString.Include
    private Date startDateProduction;

    @Column(name = "max_speed")
    @ToString.Include
    private Integer maxSpeed;

    @Column(name = "price")
    private Integer price;

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id.toString());
        map.put("name", name);
        map.put("wear", wear.toString());
        map.put("type", type.toString());
        map.put("group", group.toString());
        map.put("gameLevel", gameLevel.toString());
        map.put("cityType", cityType.toString());
        map.put("isRepaired", isRepaired.toString());
        map.put("mass", mass);
        map.put("reliability", start_reliability);
        map.put("power", power);
        map.put("speed", speed);
        map.put("maxMass", maxMass);
        map.put("maxSpeed", maxSpeed);
        map.put("startDateProduction", startDateProduction);
        map.put("price", price);

        List<String> buttons = new ArrayList<>();
        map.put("buttons", buttons);

        return map;
    }

    public static List<String> getFields() {

        List<String> fields = new ArrayList<>();

        fields.add("id");
        fields.add("name");
        fields.add("wear");
        fields.add("type");
        fields.add("isRepaired");

        return fields;
    }
}
