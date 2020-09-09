package ru.tsystems.school.dao;

import ru.tsystems.school.model.Schedule;

import java.util.List;

public interface ScheduleDao {

    List<Schedule> findAll();

    Schedule findById(int id);

    void save(Schedule schedule);

    void deleteById(int id);
    
    List<Schedule> findSchedulesByTrainId(int id);
    
    List<Schedule> findSchedulesByStationId(int id);

    void deleteTrainFromSchedule(int trainId, int stationId);
    

}
