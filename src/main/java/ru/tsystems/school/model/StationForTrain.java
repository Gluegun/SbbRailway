package ru.tsystems.school.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "stations_for_trains")
public class StationForTrain extends AbstractPo implements Serializable {

    @OneToOne
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    @OneToOne
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}
