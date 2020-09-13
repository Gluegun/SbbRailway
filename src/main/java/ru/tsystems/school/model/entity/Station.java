package ru.tsystems.school.model.entity;

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

    @OneToMany(mappedBy = "station")
    @ToString.Exclude
    private Set<Schedule> schedules;

    public Station(String name) {
        this.name = name;
    }
}
