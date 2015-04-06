package by.aplevich.horcerace.datamodel;

import javax.persistence.*;
import java.util.List;

/**
 * Places of race
 */
@Entity
public class Place extends AbstractEntity {
    @Column
    private String name;

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