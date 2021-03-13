package net.vrakin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="order_state")
@Data
@NoArgsConstructor
public class OrderState implements ShowContentsInList{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name = "in_motion")
    private Boolean inMotion;

    public OrderState(String name) {
        this.name = name;
    }

    public OrderState(Long id, String name, boolean inMotion) {
        this.id = id;
        this.name = name;
        this.inMotion = inMotion;
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
        fields.add("inMotion");

        return fields;
    }
}
