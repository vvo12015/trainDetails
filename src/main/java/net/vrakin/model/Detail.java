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

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinColumn(name="train_id")
    private Train train;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinColumn(name="detail_museum_id")
    private DetailMuseum detailMuseum;

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

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public DetailMuseum getDetailMuseum() {
        return detailMuseum;
    }

    public void setDetailMuseum(DetailMuseum detailMuseum) {
        this.detailMuseum = detailMuseum;
    }

    public Detail(Train train, DetailMuseum detailMuseum) {
        this.name = detailMuseum.getName();
        this.train = train;
        this.detailMuseum = detailMuseum;
        this.distance_from_creation = 0;
        this.distance_from_repair = 0;
        this.state = 100;
    }

    public Detail() {
    }

    @Override
    public String toString() {
        return "Detail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state=" + state +
                ", distance_from_creation=" + distance_from_creation +
                ", distance_from_repair=" + distance_from_repair +
                ", train=" + train +
                ", detailMuseum=" + detailMuseum +
                '}';
    }

    @Override
    public Map<String, Object> toMap() {

        Map<String , Object> result = new HashMap<>();

        result.put("id",  id);
        result.put("name", name);
        result.put("state", state.toString());
        result.put("distance_from_creation", distance_from_creation);
        result.put("distance_from_repair", distance_from_repair);
        List<String> buttons = new ArrayList<>();
        result.put("buttons", buttons);
        result.put("train", train.getName());
        result.put("detailMuseum", detailMuseum.getName());

        return result;
    }

    public static List<String> getFields() {

        List<String> fields = new ArrayList<>();

        fields.add("id");
        fields.add("name");
        fields.add("state");
        fields.add("distance_from_creation");
        fields.add("distance_from_repair");
        fields.add("train");
        fields.add("detailMuseum");

        return fields;
    }

}
