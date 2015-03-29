package by.aplevich.horcerace.datamodel;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * Details of race
 */
@Entity
public class Race {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String desc;
    @Column
    private String distance;
    @Column
    private Date start;
    @Column
    private Integer quantity;
    //TODO
    private List<Bet> bets;
    //TODO
    private List<Runner> runners;
    @Column
    private Place place;

    public Long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String name) {
        this.desc = desc;
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

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    public List<Runner> getRunners() {
        return runners;
    }

    public void setRunners(List<Runner> runners) {
        this.runners = runners;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
