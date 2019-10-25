package net.vrakin.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="train_status")
public class TrainStatus implements ShowContentsInList{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;

    public TrainStatus() {
    }

    public TrainStatus(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public TrainStatus(String name) {
        this.name = name;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainStatus that = (TrainStatus) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id.toString());
        map.put("name", name);
        List<String> buttons = new ArrayList<>();
        map.put("buttons", buttons);

        return map;
    }

    public static List<String> getFields() {

        List<String> fields = new ArrayList<>();

        fields.add("id");
        fields.add("name");

        return fields;
    }
}
