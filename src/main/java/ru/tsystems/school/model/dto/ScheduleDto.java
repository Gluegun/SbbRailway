package ru.tsystems.school.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleDto implements Comparable<ScheduleDto> {

    private int id;
    private TrainDto train;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private StationDto station;


    public ScheduleDto(TrainDto train, LocalTime departureTime, LocalTime arrivalTime, StationDto station) {
        this.train = train;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.station = station;
    }

    @Override
    public int compareTo(ScheduleDto o) {

        if (this.arrivalTime.isBefore(o.getArrivalTime())) return -1;
        if (this.arrivalTime.isAfter(o.getArrivalTime())) return 1;
        return 0;

    }
}
