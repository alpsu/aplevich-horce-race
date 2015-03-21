package by.aplevich.horcerace.datamodel;

import java.util.List;

/**
 * Places of race
 */
public class Place {

    private Long id;

    private String name;

    private List<Race> races;

    public Long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Race> getRaces() {
        return races;
    }

    public void setRaces(List<Race> races) {
        this.races = races;
    }
}
