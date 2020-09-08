package ru.tsystems.school.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class TrainDto {

    private int id;
    private String trainNumber;
    private int seatsAmount;

    public TrainDto(String trainNumber, int seatsAmount) {
        this.trainNumber = trainNumber;
        this.seatsAmount = seatsAmount;
    }

    public TrainDto() {

    }
}
