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
    private StationDto station;

    public ScheduleDto() {

    }

    public ScheduleDto(TrainDto train, LocalTime departureTime, StationDto station) {
        this.train = train;
        this.setDepartureTime(departureTime);
        this.station = station;
    }


}
