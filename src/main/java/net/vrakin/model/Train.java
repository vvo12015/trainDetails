package net.vrakin.model;
/*
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "train")*/
public class Train {
/*
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "creationDate")
    private Date creationDate;

    @Column(name="corpsState")
    private Byte corpsState;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Train(Long id, String name, Date creationDate, Float corpsState, User user) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.corpsState = corpsState;
        this.user = user;
    }

    public Train(String name, Date creationDate, Float corpsState, User user) {
        this.name = name;
        this.creationDate = creationDate;
        this.corpsState = corpsState;
        this.user = user;
    }

    public Train() {
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Float getCorpsState() {
        return corpsState;
    }

    public void setCorpsState(Float corpsState) {
        this.corpsState = corpsState;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return Objects.equals(name, train.name) &&
                Objects.equals(creationDate, train.creationDate) &&
                Objects.equals(corpsState, train.corpsState) &&
                Objects.equals(user, train.user);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, creationDate, corpsState, user);
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", corpsState=" + corpsState +
                ", user=" + user +
                '}';
    }*/
}
