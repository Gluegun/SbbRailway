package ru.tsystems.school.service;

import ru.tsystems.school.dto.ScheduleDto;
import ru.tsystems.school.dto.ScheduleDtoRest;

import java.util.List;

public interface ScheduleService {

    List<ScheduleDto> findAll();

    void deleteById(int id);

    List<ScheduleDto> findSchedulesDtoByTrainId(int id);

    List<ScheduleDtoRest> findScheduleByStationId(int stationId);

    void deleteTrainFromSchedule(int trainId, int stationId);

    void delayTrain(int trainId, int stationId, int minutesAmount);

}
