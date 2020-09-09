package ru.tsystems.school.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class ScheduleDto {

    private int id;
    private TrainDto train;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private StationDto station;

    public ScheduleDto() {

    }

    public ScheduleDto(TrainDto train, LocalTime departureTime, LocalTime arrivalTime, StationDto station) {
        this.train = train;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.station = station;
    }
}
