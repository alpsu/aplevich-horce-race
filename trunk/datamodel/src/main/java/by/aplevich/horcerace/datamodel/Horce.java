package by.aplevich.horcerace.datamodel;

import javax.persistence.*;

/**
 * Horce details
 */
@Entity
public class Horce extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String trainer;
    @Column
    private Integer age;

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

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Horce{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", trainer='" + trainer + '\'' +
                ", age=" + age +
                '}';
    }
}