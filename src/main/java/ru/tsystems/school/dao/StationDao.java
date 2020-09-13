package ru.tsystems.school.dao;

import ru.tsystems.school.model.entity.Schedule;
import ru.tsystems.school.model.entity.Station;
import ru.tsystems.school.model.entity.Train;

import java.time.LocalTime;
import java.util.List;

public interface StationDao {

    List<Station> findAll();

    Station findById(int id);

    void save(Station station);

    void update(Station station);

    void update(int id, Station station);

    void deleteById(int id);

    List<Train> findAllTrainsForCurrentStation(int id);

    List<Schedule> findScheduleForStation(int id);

    List<Train> findSuitableTrains(Station from, Station to, String fromTime, String toTime);

    Station findByStationName(String name);

    void addSchedule(int stationId, int trainId, LocalTime arrivalTime, LocalTime departureTime);

    void saveSchedule(Schedule schedule);

    List<Schedule> findScheduleForStationAndTrain(int stationId, int trainId);
}
