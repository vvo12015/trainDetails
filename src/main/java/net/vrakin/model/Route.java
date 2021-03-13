package net.vrakin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="route")
@Data
@NoArgsConstructor
public class Route implements ShowContentsInList{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private Long id;

    @ManyToOne
    @JoinColumn(name="city1")
    private City city1;

    @ManyToOne
    @JoinColumn(name="city2")
    private City city2;

    @Column(name="distance")
    private Integer distance;

    public Route(Long id, City city1, City city2, Integer distance) {
        this.id = id;
        this.city1 = city1;
        this.city2 = city2;
        this.distance = distance;
    }

    public Route(City city1, City city2, Integer distance) {
        this.city1 = city1;
        this.city2 = city2;
        this.distance = distance;
    }

    @Override
    public Map<String, Object> toMap() {
        
         Map<String, Object> map = new HashMap<>();

        map.put("id", id.toString());
        map.put("city1", city1.toString());
        map.put("city2", city2.toString());
        map.put("distance", distance.toString());
        map.put("name", getName());
        List<String> buttons = new ArrayList<>();
        map.put("buttons", buttons);

         return map;
    }

    @Override
    public String getName() {
        return city1.getName() + '-' + city2.getName() + ". Distance: " + distance;
    }

    public static List<String> getFields() {

        List<String> fields = new ArrayList<>();

        fields.add("id");
        fields.add("city1");
        fields.add("city2");
        fields.add("distance");
        fields.add("name");

        return fields;
    }

}
