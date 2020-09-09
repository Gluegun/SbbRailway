package ru.tsystems.school.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "stations")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Station extends AbstractPo implements Serializable {

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "stations_for_trains",
            joinColumns = @JoinColumn(name = "station_id"),
            inverseJoinColumns = @JoinColumn(name = "train_id")
    )
    private Set<Train> trains;

    @OneToMany(mappedBy = "station")
    @ToString.Exclude
    private Set<Schedule> schedules;

    @ManyToMany(mappedBy = "stations")
    private Set<Route> routes;

    public Station(String name) {
        this.name = name;
    }


}
