package by.aplevich.horcerace.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Details of runner
 */
@Entity
public class Runner {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private double koefficient;
    @Column
    private Integer place;
    @Column
    private Horce horce;
    @Column
    private Jockey jockey;
    @Column
    private Race race;

    public Long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public double getKoefficient() {
        return koefficient;
    }

    public void setKoefficient(double koefficient) {
        this.koefficient = koefficient;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public Horce getHorce() {
        return horce;
    }

    public void setHorce(Horce horce) {
        this.horce = horce;
    }

    public Jockey getJockey() {
        return jockey;
    }

    public void setJockey(Jockey jockey) {
        this.jockey = jockey;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }
}
