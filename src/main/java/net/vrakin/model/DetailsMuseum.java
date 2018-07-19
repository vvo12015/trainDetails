package net.vrakin.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="detail_museum")
public class DetailsMuseum {

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

    @ManyToMany(mappedBy = "details")
    private List<TrainMuseum> trainMuseum = new ArrayList<>();

    public DetailsMuseum(Long id, String name, Byte wear, String type, Boolean isRepaired, List<TrainMuseum> trainMuseum) {
        this.id = id;
        this.name = name;
        this.wear = wear;
        this.type = type;
        this.isRepaired = isRepaired;
        this.trainMuseum = trainMuseum;
    }

    public DetailsMuseum(String name, Byte wear, String type, Boolean isRepaired, List<TrainMuseum> trainMuseum) {
        this.name = name;
        this.wear = wear;
        this.type = type;
        this.isRepaired = isRepaired;
        this.trainMuseum = trainMuseum;
    }

    public DetailsMuseum() {
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

    public Boolean getRepaired() {
        return isRepaired;
    }

    public void setRepaired(Boolean repaired) {
        isRepaired = repaired;
    }

    public List<TrainMuseum> getTrainMuseum() {
        return trainMuseum;
    }

    public void setTrainMuseum(List<TrainMuseum> trainMuseum) {
        this.trainMuseum = trainMuseum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailsMuseum that = (DetailsMuseum) o;
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
        return "DetailsMuseum{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", wear=" + wear +
                ", type='" + type + '\'' +
                ", isRepaired=" + isRepaired +
                '}';
    }
}
