package net.vrakin.model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name = "train")
@Data
@NoArgsConstructor
public class Train implements ShowContentsInList{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @EqualsAndHashCode.Exclude
    @ToString.Include
    private Long id;

    @Column(name = "name")
    @ToString.Include
    private String name;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    @ToString.Include
    private Calendar creationDate;

    @Column(name="corps_state")
    @ToString.Include
    private Byte corpsState;

    @ManyToOne(fetch = FetchType.EAGER,
    cascade = CascadeType.ALL)
    @JoinColumn(name="company_id")
    @ToString.Exclude
    private Company company;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="train_museum_id")
    @ToString.Exclude
    private TrainMuseum trainMuseum;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="city_id")
    @ToString.Exclude
    private City city;

    @OneToMany(fetch = FetchType.EAGER, mappedBy="train")
    @ToString.Exclude
    private List<Detail> details=new ArrayList<>();

    public Train(String name, Company company, TrainMuseum trainMuseum) {
        this.name = name;
        this.company = company;
        this.corpsState = 100;
        this.trainMuseum = trainMuseum;
    }

    @Override
    public Map<String, Object> toMap() {
        
        Map<String, Object> map = new HashMap<>();
        
        map.put("id", id.toString());
        map.put("name", name);
        map.put("corpsState", corpsState.toString());
        map.put("company", company.toString());
        map.put("trainMuseum", trainMuseum.toString());
        map.put("city", city.toString());
        DateFormat format = new SimpleDateFormat("dd:MM:yyyy");
        map.put("creationDate", format.format(creationDate.getTime()));
        List<String> buttons = new ArrayList<>();

        buttons.add("buy");
        buttons.add("details");

        map.put("buttons", buttons);

        return map;
    }

    public static List<String> getFields() {

        List<String> fields = new ArrayList<>();

        fields.add("id");
        fields.add("name");
        fields.add("corpsState");
        fields.add("company");
        fields.add("trainMuseum");
        fields.add("city");
        fields.add("creationDate");

        return fields;
    }

    public List<Detail> getDetails() {
        return details;
    }
}
