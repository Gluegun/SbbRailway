package ru.tsystems.school.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TrainDto {

    private int id;
    private String trainNumber;
    private int seatsAmount;
    private List<StationDto> stations;
    private List<PassengerDto> passengers;
    private List<ScheduleDto> schedules;

    public TrainDto(String trainNumber, int seatsAmount) {
        this.trainNumber = trainNumber;
        this.seatsAmount = seatsAmount;
    }

    public TrainDto() {
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((passengers == null) ? 0 : passengers.hashCode());
        result = prime * result + ((schedules == null) ? 0 : schedules.hashCode());
        result = prime * result + seatsAmount;
        result = prime * result + ((stations == null) ? 0 : stations.hashCode());
        result = prime * result + ((trainNumber == null) ? 0 : trainNumber.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TrainDto other = (TrainDto) obj;
        if (passengers == null) {
            if (other.passengers != null)
                return false;
        } else if (!passengers.equals(other.passengers))
            return false;
        if (schedules == null) {
            if (other.schedules != null)
                return false;
        } else if (!schedules.equals(other.schedules))
            return false;
        if (seatsAmount != other.seatsAmount)
            return false;
        if (stations == null) {
            if (other.stations != null)
                return false;
        } else if (!stations.equals(other.stations))
            return false;
        if (trainNumber == null) {
            if (other.trainNumber != null)
                return false;
        } else if (!trainNumber.equals(other.trainNumber))
            return false;
        return true;
    }

}
