package ru.tsystems.school.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Table(name = "schedule")
@Getter
@Setter
public class Schedule extends AbstractPo implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @Column(name = "arrival_time")
    private LocalTime arrivalTime;

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

    public Schedule(Train train, LocalTime departureTime, Station station) {
        this.train = train;
        this.departureTime = departureTime;
        this.station = station;
    }

    public Schedule() {
    }
}
