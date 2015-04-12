package by.aplevich.horcerace.datamodel;

import javax.persistence.*;

/**
 * Places of race
 */
@Entity
public class Place extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                '}';
    }
}