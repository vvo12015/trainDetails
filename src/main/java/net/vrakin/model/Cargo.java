package net.vrakin.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="cargo")
@NoArgsConstructor
@Data
public class Cargo implements ShowContentsInList{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "min_price", columnDefinition = "NUMERIC(10,2)")
    private Double minPrice;

    @Column(name = "max_price", columnDefinition = "NUMERIC(10,2)")
    private Double maxPrice;

    public Cargo(String name) {
        this.name = name;
    }

    public Cargo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id.toString());
        map.put("name", name);
        map.put("minPrice", minPrice.toString());
        map.put("maxPrice", maxPrice.toString());
        List<String> buttons = new ArrayList<>();
        map.put("buttons", buttons);

        return map;
    }

    public static List<String> getFields(){
        List<String> fields = new ArrayList<>();

        fields.add("id");
        fields.add("name");
        fields.add("minPrice");
        fields.add("maxPrice");

        return fields;
    }
}
