package net.vrakin.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="city1")
    private City city1;

    @ManyToOne
    @JoinColumn(name="city2")
    private City city2;

    @Column(name="distance")
    private Integer distance;

    public Route(Long id, City city1, City city2, Integer distance) {
        this.id = id;
        this.city1 = city1;
        this.city2 = city2;
        this.distance = distance;
    }

    public Route(City city1, City city2, Integer distance) {
        this.city1 = city1;
        this.city2 = city2;
        this.distance = distance;
    }

    public Route() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(city1, route.city1) &&
                Objects.equals(city2, route.city2) &&
                Objects.equals(distance, route.distance);
    }

    @Override
    public int hashCode() {

        return Objects.hash(city1, city2, distance);
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", city1=" + city1 +
                ", city2=" + city2 +
                ", distance=" + distance +
                '}';
    }
}
