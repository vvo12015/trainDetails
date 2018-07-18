package net.vrakin.model;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "train")
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name="corps_state")
    private Byte corpsState;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "train_museum_id")
    private TrainMuseum trainMuseum;

    public Train(Long id, String name, Date creationDate, Byte corpsState, User user, TrainMuseum trainMuseum) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.corpsState = corpsState;
        this.user = user;
        this.trainMuseum = trainMuseum;
    }

    public Train(String name, Date creationDate, Byte corpsState, User user, TrainMuseum trainMuseum) {
        this.name = name;
        this.creationDate = creationDate;
        this.corpsState = corpsState;
        this.user = user;
        this.trainMuseum = trainMuseum;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Byte getCorpsState() {
        return corpsState;
    }

    public void setCorpsState(Byte corpsState) {
        this.corpsState = corpsState;
    }

    public TrainMuseum getTrainMuseum() {
        return trainMuseum;
    }

    public void setTrainMuseum(TrainMuseum trainMuseum) {
        this.trainMuseum = trainMuseum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return Objects.equals(name, train.name) &&
                Objects.equals(creationDate, train.creationDate) &&
                Objects.equals(corpsState, train.corpsState) &&
                Objects.equals(user, train.user) &&
                Objects.equals(trainMuseum, train.trainMuseum);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, creationDate, corpsState, user, trainMuseum);
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", corpsState=" + corpsState +
                ", user=" + user +
                ", trainMuseum=" + trainMuseum +
                '}';
    }
}
