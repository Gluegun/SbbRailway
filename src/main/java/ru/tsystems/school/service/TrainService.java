package ru.tsystems.school.service;

import ru.tsystems.school.dto.PassengerDto;
import ru.tsystems.school.dto.StationDto;
import ru.tsystems.school.dto.TrainDto;

import java.util.List;

public interface TrainService {

    List<TrainDto> findAllDtoTrains();

    TrainDto findTrainById(int id);

    void save(TrainDto trainDto);

    void deleteById(int id);

    List<StationDto> findAllStations(int id);

    List<PassengerDto> findAllPassengers(int id);

    List<TrainDto> findTrainDtoByNumber(String number);

    void update(TrainDto trainDto);

    void addStationToTrain(String departureTime, String station, TrainDto trainDto, int id);

    List <StationDto> potentialStationsForTrain(int trainId);

}
