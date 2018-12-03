package net.vrakin.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "detail")
public class Detail implements ShowContentsInList{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="state")
    private Byte state;

    @Column(name="distance_from_creation")
    private Integer distance_from_creation;

    @Column(name="distance_from_repair")
    private Integer distance_from_repair;

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

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Integer getDistance_from_creation() {
        return distance_from_creation;
    }

    public void setDistance_from_creation(Integer distance_from_creation) {
        this.distance_from_creation = distance_from_creation;
    }

    public Integer getDistance_from_repair() {
        return distance_from_repair;
    }

    public void setDistance_from_repair(Integer distance_from_repair) {
        this.distance_from_repair = distance_from_repair;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Detail detail = (Detail) o;
        return Objects.equals(name, detail.name) &&
                Objects.equals(state, detail.state) &&
                Objects.equals(distance_from_creation, detail.distance_from_creation) &&
                Objects.equals(distance_from_repair, detail.distance_from_repair);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, state, distance_from_creation, distance_from_repair);
    }

    @Override
    public String toString() {
        return "Detail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state=" + state +
                ", distance_from_creation=" + distance_from_creation +
                ", distance_from_repair=" + distance_from_repair +
                '}';
    }

    @Override
    public Map<String, Object> toMap() {

        Map<String , Object> result = new HashMap<>();

                result.put("id",  id);
                result.put("name", name);
                result.put("state", state);
                result.put("distance_from_creation", distance_from_creation);
                result.put("distance_from_repair", distance_from_repair);
        List<String> buttons = new ArrayList<>();
        result.put("buttons", buttons);
        
        return result;
    }

    public static List<String> getFields() {

        List<String> fields = new ArrayList<>();

        fields.add("id");
        fields.add("name");
        fields.add("state");
        fields.add("distance_from_creation");
        fields.add("distance_from_repair");

        return fields;
    }

}
