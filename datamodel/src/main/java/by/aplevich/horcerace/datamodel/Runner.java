package by.aplevich.horcerace.datamodel;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Details of runner
 */
@Entity
public class Runner extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    @Min(value = 0)
    private double koefficient;

    @Column
    @NotNull
    @Min(value = 0)
    @Max(value = 15)
    private Integer place;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Horce.class)
    @NotNull
    private Horce horce;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Jockey.class)
    @NotNull
    private Jockey jockey;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Race.class)
    @NotNull
    private Race race;

    @Override
    public Long getId() {
        return id;
    }

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