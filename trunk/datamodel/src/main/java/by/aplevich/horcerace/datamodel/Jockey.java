package by.aplevich.horcerace.datamodel;

import javax.persistence.*;

/**
 * Jockey details
 */
@Entity
public class Jockey extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String fname;
    @Column
    private String lname;

    @Override
    public Long getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    @Override
    public String toString() {
        return "Jockey{" +
                "id=" + getId() +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                '}';
    }
}