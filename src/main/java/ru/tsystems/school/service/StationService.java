package ru.tsystems.school.service;

import ru.tsystems.school.dto.ScheduleDto;
import ru.tsystems.school.dto.StationDto;
import ru.tsystems.school.dto.TrainDto;

import java.util.List;

public interface StationService {

    List<StationDto> findAllStations();

    StationDto findStationById(int id);

    void save(StationDto stationDto);

    void update(StationDto stationDto);

    void deleteStationById(int id);

    List<TrainDto> findAllTrainsForCurrentStation(int id);

    List<ScheduleDto> findAllSchedulesForStation(int id);

    List<TrainDto> findSuitableTrains(StationDto from, StationDto to, String fromTime, String toTime);

    StationDto findByStationName(String name);

    List<ScheduleDto> findScheduleForStationAndTrain(int stationId, int trainId);

    void saveSchedule(ScheduleDto schedule);

    List<TrainDto> potentialTrainsForStation(int stationId);

    void addTrainToStation(String departureTime,
                           String train, StationDto stationDto,
                           int id);

}
