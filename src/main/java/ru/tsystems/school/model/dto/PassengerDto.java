package ru.tsystems.school.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PassengerDto {

    private int id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String username;


}
