package net.vrakin.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="cash")
    private Float cash;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy="company")
    private List<Train> trains=new ArrayList<>();

    private Integer trainCount;

    public Company(Long id, String name, Float cash, User user, List<Train> trains) {
        this.id = id;
        this.name = name;
        this.cash = cash;
        this.user = user;
        this.trains = trains;
    }

    public Company(String name, Float cash, User user, List<Train> trains) {
        this.name = name;
        this.cash = cash;
        this.user = user;
        this.trains = trains;
    }

    public Company(String name, Float cash, User user) {
        this.name = name;
        this.cash = cash;
        this.user = user;
    }

    public Company() {
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

    public Float getCash() {
        return cash;
    }

    public void setCash(Float cash) {
        this.cash = cash;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getTrainCount() {
        return trains.size();
    }

    public List<Train> getTrains() {
        return trains;
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id) &&
                Objects.equals(name, company.name) &&
                Objects.equals(cash, company.cash) &&
                Objects.equals(user, company.user) &&
                Objects.equals(trains, company.trains);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, cash, user, trains);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cash=" + cash +
                ", user=" + user +
                ", trains=" + trains +
                '}';
    }
}
