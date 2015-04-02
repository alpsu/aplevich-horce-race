package by.aplevich.horcerace.datamodel;

import javax.persistence.*;
import java.util.Date;

/**
 * Details of race
 */
@Entity
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @Column
    private String distance;

    @Column
    private Date start;

    @Column
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Place.class)
    private Place place;

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String name) {
        this.description = description;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "Race{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", distance='" + distance + '\'' +
                ", start=" + start +
                ", quantity=" + quantity +
                ", place=" + place +
                '}';
    }
}