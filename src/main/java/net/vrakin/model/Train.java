package net.vrakin.model;
import org.hibernate.annotations.CreationTimestamp;

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

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Calendar creationDate;

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

    @OneToMany(fetch = FetchType.EAGER, mappedBy="train")
    private List<Detail> details=new ArrayList<>();

    public Train(String name, Company company, TrainMuseum trainMuseum) {
        this.name = name;
        this.company = company;
        this.corpsState = 100;
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

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
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

    public void setDetails(List<Detail> details) {
        this.details = details;
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
