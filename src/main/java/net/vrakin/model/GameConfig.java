package net.vrakin.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="game_config")
public class GameConfig implements ShowContentsInList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="value_str")
    private String valueStr;

    @Column(name="value_int")
    private Integer valueInt;

    public GameConfig(String name, String valueStr) {
        this.name = name;
        this.valueStr = valueStr;
    }

    public GameConfig(String name, Integer valueInt) {
        this.name = name;
        this.valueInt = valueInt;
    }

    public GameConfig() {
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

    public String getValueStr() {
        return valueStr;
    }

    public void setValueStr(String valueStr) {
        this.valueStr = valueStr;
    }

    public Integer getValueInt() {
        return valueInt;
    }

    public void setValueInt(Integer valueInt) {
        this.valueInt = valueInt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameConfig gconf = (GameConfig) o;
        return Objects.equals(id, gconf.id) &&
                Objects.equals(name, gconf.name) &&
                Objects.equals(valueStr, gconf.valueStr) &&
                Objects.equals(valueInt, gconf.valueInt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, valueStr, valueInt);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ((valueStr != null)?(", valueStr=" + valueStr):
                (", valueInt=" + valueInt)) +
                '}';
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id.toString());
        map.put("name", name);
        map.put("valueStr", valueStr);
        map.put("valueInt", valueInt);
        List<String> buttons = new ArrayList<>();
        map.put("buttons", buttons);

        return map;
    }

    public static List<String> getFields() {

        List<String> fields = new ArrayList<>();

        fields.add("id");
        fields.add("name");
        fields.add("valueStr");
        fields.add("valueInt");

        return fields;
    }
}
