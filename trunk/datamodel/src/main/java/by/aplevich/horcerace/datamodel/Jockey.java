package by.aplevich.horcerace.datamodel;

import javax.persistence.*;

/**
 * Jockey details
 */
@Entity
public class Jockey extends AbstractEntity {
    @Column
    private String fName;
    @Column
    private String lName;

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    @Override
    public String toString() {
        return "Jockey{" +
                "id=" + getId() +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                '}';
    }
}