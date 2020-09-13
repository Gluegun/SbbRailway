package ru.tsystems.school.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ScheduleDtoRest {

    private int id;
    private TrainDto train;
    private StationDto station;
    private String departureTime;
    private String arrivalTime;


    public ScheduleDtoRest(ScheduleDto scheduleDto) {

        this.id = scheduleDto.getId();
        this.train = scheduleDto.getTrain();
        this.station = scheduleDto.getStation();
        this.departureTime = convertLocalTimeToString(scheduleDto.getDepartureTime());
        this.arrivalTime = convertLocalTimeToString(scheduleDto.getArrivalTime());

    }

    private String convertLocalTimeToString(LocalTime localTime) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return localTime.format(dateTimeFormatter);

    }

}
