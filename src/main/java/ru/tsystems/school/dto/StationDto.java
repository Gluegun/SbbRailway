package ru.tsystems.school.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StationDto {

    private int id;
    private String name;
    private List<TrainDto> trains;
    private List<ScheduleDto> schedules;

    public StationDto(String name) {
        this.name = name;
    }

    public StationDto(String name, List<TrainDto> trains, List<ScheduleDto> schedules) {
        this.name = name;
        this.trains = trains;
        this.schedules = schedules;
    }

    public StationDto() {

    }

}
