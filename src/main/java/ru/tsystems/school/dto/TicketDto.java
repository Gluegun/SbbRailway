package ru.tsystems.school.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class TicketDto {

    private int id;
    private TrainDto train;
    private LocalTime departureTime;
    private PassengerDto passenger;


    public TicketDto(TrainDto train, LocalTime departureTime, PassengerDto passenger) {
        super();
        this.train = train;
        this.departureTime = departureTime;
        this.passenger = passenger;
    }

    public TicketDto() {

    }
}
