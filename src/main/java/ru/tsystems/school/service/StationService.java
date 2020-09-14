package ru.tsystems.school.service;

import ru.tsystems.school.model.dto.ScheduleDto;
import ru.tsystems.school.model.dto.StationDto;
import ru.tsystems.school.model.dto.TrainDto;

import java.util.List;

/**
 * Service for stations
 */

public interface StationService {

    List<StationDto> findAllStations();

    StationDto findStationById(int id);

    /**
     * @param stationDto
     * saves new station to db
     */

    void save(StationDto stationDto);

    void update(StationDto stationDto);

    void update(int id, StationDto stationDto);

    void deleteStationById(int id);

    List<TrainDto> findAllTrainsForCurrentStation(int id);

    List<ScheduleDto> findAllSchedulesForStation(int id);

    /**
     *
     * @param from provides info what station user wants to travel from
     * @param to provides info what station user wants to travel to
     * @param fromTime provides info from what time train should departs from station
     * @param toTime provides info to what time train should departs from station
     * @return list of suitable trains
     */

    List<TrainDto> findSuitableTrains(StationDto from, StationDto to, String fromTime, String toTime);

    StationDto findByStationName(String name);

    /**
     *
     * @param stationId refers to station id
     * @param trainId refers to train id
     * @return Schedule to specific train and station
     */

    List<ScheduleDto> findScheduleForStationAndTrain(int stationId, int trainId);

    void saveSchedule(ScheduleDto schedule);

    /**
     *
     * @param stationId refers to train
     * @return list of trains suitable for current station
     */

    List<TrainDto> potentialTrainsForStation(int stationId);

    void addTrainToStation(String departureTime, String arrivalTime,
                           String train, StationDto stationDto,
                           int id);

}
