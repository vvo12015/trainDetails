package net.vrakin.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="order_state")
public class OrderState implements ShowContentsInList{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name = "in_motion")
    private Boolean inMotion;

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

    public Boolean getInMotion() {
        return inMotion;
    }

    public void setInMotion(Boolean depo) {
        inMotion = depo;
    }

    public OrderState() {
    }

    public OrderState(String name) {
        this.name = name;
    }

    public OrderState(Long id, String name, boolean inMotion) {
        this.id = id;
        this.name = name;
        this.inMotion = inMotion;
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("id", id.toString());
        map.put("name", name);

        return map;
    }

    @Override
    public List<String> getFields() {

        List<String> fields = new ArrayList<>();

        fields.add("id");
        fields.add("name");
        fields.add("inMotion");

        return fields;
    }
}
