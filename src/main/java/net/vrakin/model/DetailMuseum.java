package net.vrakin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
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

    @Column(name="type")
    @ToString.Include
    private String type;

    @Column(name="is_repaired")
    @ToString.Include
    private Boolean isRepaired;

    @Column(name = "mass")
    @ToString.Include
    private Integer mass;

    @Column(name = "reliability")
    private Integer reliability;

    @Column(name = "power")
    private Integer power;

    public DetailMuseum(Long id, String name, Byte wear, String type, Boolean isRepaired) {
        this.id = id;
        this.name = name;
        this.wear = wear;
        this.type = type;
        this.isRepaired = isRepaired;
    }

    public DetailMuseum(String name, Byte wear, String type, Boolean isRepaired, List<TrainMuseum> trainMuseum) {
        this.name = name;
        this.wear = wear;
        this.type = type;
        this.isRepaired = isRepaired;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id.toString());
        map.put("name", name);
        map.put("wear", wear.toString());
        map.put("type", type);
        map.put("isRepaired", isRepaired.toString());
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
