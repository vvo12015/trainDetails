package net.vrakin.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="detail_museum")
public class DetailMuseum implements ShowContentsInList{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="wear")
    private Byte wear;

    @Column(name="type")
    private String type;

    @Column(name="is_repaired")
    private Boolean isRepaired;

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

    public DetailMuseum() {
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

    public Byte getWear() {
        return wear;
    }

    public void setWear(Byte wear) {
        this.wear = wear;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIsRepaired() {
        return isRepaired;
    }

    public void setIsRepaired(Boolean repaired) {
        isRepaired = repaired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailMuseum that = (DetailMuseum) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(wear, that.wear) &&
                Objects.equals(type, that.type) &&
                Objects.equals(isRepaired, that.isRepaired);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, wear, type, isRepaired);
    }

    @Override
    public String toString() {
        return "DetailMuseum{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", wear=" + wear +
                ", type='" + type + '\'' +
                ", isRepaired=" + isRepaired +
                '}';
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("id", id.toString());
        map.put("name", name);
        map.put("wear", wear.toString());
        map.put("type", type);
        map.put("isRepaired", isRepaired.toString());

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
