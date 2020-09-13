package ru.tsystems.school.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StationDto {

    private int id;
    private String name;

    public StationDto(String name) {
        this.name = name;
    }

}
