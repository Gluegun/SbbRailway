package ru.tsystems.school.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Table(name = "schedule")
@Getter
@Setter
@NoArgsConstructor
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

    public Schedule(Train train, LocalTime arrivalTime, LocalTime departureTime, Station station) {
        this.train = train;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.station = station;
    }

}
