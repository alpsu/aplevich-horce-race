package by.aplevich.horcerace.datamodel;

import javax.persistence.*;

/**
 * Details of runner
 */
@Entity
public class Runner extends AbstractEntity {
    @Column
    private double koefficient;

    @Column
    private Integer place;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Horce.class)
    private Horce horce;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Jockey.class)
    private Jockey jockey;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Race.class)
    private Race race;

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

    @Override
    public String toString() {
        return "Runner{" +
                "id=" + getId() +
                ", koefficient=" + koefficient +
                ", place=" + place +
                ", horce=" + horce +
                ", jockey=" + jockey +
                ", race=" + race +
                '}';
    }
}