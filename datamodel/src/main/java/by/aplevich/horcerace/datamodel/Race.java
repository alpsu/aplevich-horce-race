package by.aplevich.horcerace.datamodel;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Details of race
 */
@Entity
public class Race extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    @Size(max = 250)
    private String description;

    @Column
    @NotNull
    @Size(max = 50)
    private String distance;

    @Column
    @NotNull
    private Date start;

    @Column
    @NotNull
    @Min(value = 4)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Place.class)
    @NotNull
    private Place place;

    @Override
    public Long getId() {
        return id;
    }

       public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
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
                "description='" + description + '\'' +
                ", distance='" + distance + '\'' +
                ", start=" + start +
                ", quantity=" + quantity +
                ", place=" + place +
                '}';
    }
}