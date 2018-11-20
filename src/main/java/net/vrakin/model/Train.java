package net.vrakin.model;
import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name = "train")
public class Train implements ShowContentsInList{
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

    @ManyToOne(fetch = FetchType.EAGER,
    cascade = CascadeType.ALL)
    @JoinColumn(name="company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="train_museum_id")
    private TrainMuseum trainMuseum;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="city_id")
    private City city;

    public Train(String name, Company company, TrainMuseum trainMuseum) {
        this.name = name;
        this.corpsState = 100;
        this.company = company;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return Objects.equals(name, train.name) &&
                Objects.equals(creationDate, train.creationDate) &&
                Objects.equals(corpsState, train.corpsState) &&
                Objects.equals(company, train.company) &&
                Objects.equals(trainMuseum, train.trainMuseum);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, creationDate, corpsState, company, trainMuseum);
    }

    @Override
    public String toString() {
        DateFormat format = new SimpleDateFormat("dd:MM:yyyy");
        return "Train{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDate=" + format.format(creationDate.getTime()) +
                ", corpsState=" + corpsState +
                ", company=" + company +
                ", trainMuseum=" + trainMuseum +
                '}';
    }

    @Override
    public Map<String, String> toMap() {
        
        Map<String, String> map = new HashMap<>();
        
        map.put("id", id.toString());
        map.put("name", name);
        map.put("corpsState", corpsState.toString());
        map.put("company", company.toString());
        map.put("trainMuseum", trainMuseum.toString());
        map.put("city", city.toString());
        map.put("creationDate", creationDate.toString());

        return map;
    }

    @Override
    public List<String> getFields() {

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
}
