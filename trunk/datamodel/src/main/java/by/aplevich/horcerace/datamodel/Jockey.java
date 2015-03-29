package by.aplevich.horcerace.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Jockey details
 */
@Entity
public class Jockey {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String firstNmae;
    @Column
    private String lastName;

    public Long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getFirstNmae() {
        return firstNmae;
    }

    public void setFirstNmae(String firstNmae) {
        this.firstNmae = firstNmae;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
