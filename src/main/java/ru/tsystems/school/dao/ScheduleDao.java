package ru.tsystems.school.dao;

import ru.tsystems.school.model.entity.Schedule;

import java.util.List;

public interface ScheduleDao {

    void save(Schedule schedule);

    List<Schedule> findSchedulesByTrainId(int id);

    List<Schedule> findSchedulesByStationId(int id);

    void deleteTrainFromSchedule(int trainId, int stationId);

}
