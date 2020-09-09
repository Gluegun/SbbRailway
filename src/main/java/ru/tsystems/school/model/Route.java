package ru.tsystems.school.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "routes")
public class Route extends AbstractPo implements Serializable {

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "route_station",
            joinColumns = {@JoinColumn(name = "route_id")},
            inverseJoinColumns = {@JoinColumn(name = "station_id")})
    private Set<Station> stations;

    public Set<Station> getStations() {
        return stations;
    }

    public void setStations(Set<Station> stations) {
        this.stations = stations;
    }
}
