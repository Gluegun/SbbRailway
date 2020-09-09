package ru.tsystems.school.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class StationDto {

    private int id;
    @NotNull(message = "Station name is required")
    @NotBlank(message = "Station name cannot be empty")
    private String name;

    public StationDto(String name) {
        this.name = name;
    }



    public StationDto() {

    }

}
