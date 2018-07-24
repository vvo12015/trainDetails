package net.vrakin.model;

import javax.persistence.*;

@Entity
@Table(name="order_state")
public class OrderState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

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

    public OrderState() {
    }

    public OrderState(String name) {
        this.name = name;
    }

    public OrderState(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
