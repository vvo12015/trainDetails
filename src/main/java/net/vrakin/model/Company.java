package net.vrakin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="company")
@NoArgsConstructor
@Data
public class Company implements ShowContentsInList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    @ToString.Include
    private Long id;

    @Column(name="name")
    @ToString.Include
    private String name;

    @Column(name="cash")
    @ToString.Include
    private Float cash;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    @ToString.Exclude
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy="company")
    @ToString.Exclude
    private List<Train> trains=new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="city_id")
    @ToString.Exclude
    private City city;

    private Integer trainCount;

    public Company(String name, Float cash, User user, List<Train> trains, City city) {
        this.name = name;
        this.cash = cash;
        this.user = user;
        this.city = city;
        this.trains = trains;
    }

    public Company(String name, Float cash, User user, City city) {
        this.name = name;
        this.cash = cash;
        this.user = user;
        this.city = city;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id.toString());
        map.put("name", name);
        map.put("cash", cash.toString());
        map.put("user", user.getUsername());
        List<String> buttons = new ArrayList<>();
        map.put("buttons", buttons);

        return map;
    }

    public static List<String> getFields() {

        List<String> fields = new ArrayList<>();

        fields.add("id");
        fields.add("name");
        fields.add("cash");
        fields.add("user");

        return fields;
    }
}
