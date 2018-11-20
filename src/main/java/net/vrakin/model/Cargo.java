package net.vrakin.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Table(name="cargo")
public class Cargo implements ShowContentsInList{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "min_price", columnDefinition = "NUMERIC(10,2)")
    private Double minPrice;

    @Column(name = "max_price", columnDefinition = "NUMERIC(10,2)")
    private Double maxPrice;

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

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Cargo(String name) {
        this.name = name;
    }

    public Cargo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Cargo(Long id, String name, Double minPrice, Double maxPrice) {
        this.id = id;
        this.name = name;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public Cargo(String name, Double minPrice, Double maxPrice) {
        this.name = name;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public Cargo() {
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("id", id.toString());
        map.put("name", name);
        map.put("minPrice", minPrice.toString());
        map.put("maxPrice", maxPrice.toString());

        return map;
    }

    @Override
    public List<String> getFields() {

        List<String> fields = new ArrayList<>();

        fields.add("id");
        fields.add("name");
        fields.add("minPrice");
        fields.add("maxPrice");

        return fields;
    }
}
