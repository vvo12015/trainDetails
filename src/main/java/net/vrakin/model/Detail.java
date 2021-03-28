package net.vrakin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;

@Entity
@Table(name = "detail")
@Data
@NoArgsConstructor
public class Detail implements ShowContentsInList{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    @ToString.Include
    private Long id;

    @Column(name="name")
    @ToString.Include
    private String name;

    @ManyToOne(fetch = FetchType.LAZY,
                cascade = CascadeType.ALL)
    @JoinColumn(name="state_ref")
    @ToString.Include
    private DetailStatus state;

    @Column(name="distance_from_creation")
    @ToString.Include
    private Integer distance_from_creation;

    @Column(name="distance_from_repair")
    @ToString.Include
    private Integer distance_from_repair;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinColumn(name="train_id")
    private Train train;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinColumn(name="detail_museum_id")
    private DetailMuseum detailMuseum;

    @Column(name = "reliability")
    private Integer reliability;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "start_episode_date")
    private Date startEpisodeDate;

    public Detail(Train train, DetailMuseum detailMuseum) {
        this.name = detailMuseum.getName();
        this.train = train;
        this.detailMuseum = detailMuseum;
        this.distance_from_creation = 0;
        this.distance_from_repair = 0;
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
