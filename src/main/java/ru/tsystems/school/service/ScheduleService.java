package ru.tsystems.school.service;

import ru.tsystems.school.dto.ScheduleDto;

import java.util.List;

public interface ScheduleService {

    List<ScheduleDto> findAll();

    void deleteById(int id);

    List<ScheduleDto> findSchedulesDtoByTrainId(int id);

    List<ScheduleDto> findScheduleByStationId(int stationId);

    void deleteTrainFromSchedule(int trainId, int stationId);

}
