package ru.tsystems.school.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class TicketDto {

    private int id;
    private TrainDto train;
    private LocalTime departureTime;
    private PassengerDto passenger;

}
