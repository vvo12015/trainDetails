package net.vrakin.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="route")
public class Route implements ShowContentsInList{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Route() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(city1, route.city1) &&
                Objects.equals(city2, route.city2) &&
                Objects.equals(distance, route.distance);
    }

    @Override
    public int hashCode() {

        return Objects.hash(city1, city2, distance);
    }

    @Override
    public String toString() {
        return city1.getName() + '-' + city2.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public City getCity1() {
        return city1;
    }

    public void setCity1(City city1) {
        this.city1 = city1;
    }

    public City getCity2() {
        return city2;
    }

    public void setCity2(City city2) {
        this.city2 = city2;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Override
    public Map<String, Object> toMap() {
        
         Map<String, Object> map = new HashMap<>();

        map.put("id", id.toString());
        map.put("city1", city1.toString());
        map.put("city2", city2.toString());
        map.put("distance", distance.toString());
        map.put("name", toString());

         return map;
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
