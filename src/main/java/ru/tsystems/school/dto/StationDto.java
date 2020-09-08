package ru.tsystems.school.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StationDto {

    private int id;
    private String name;

    public StationDto(String name) {
        this.name = name;
    }



    public StationDto() {

    }

}
