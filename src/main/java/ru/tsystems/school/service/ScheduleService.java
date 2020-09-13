package ru.tsystems.school.service;

import ru.tsystems.school.model.dto.ScheduleDto;
import ru.tsystems.school.model.dto.ScheduleDtoRest;

import java.util.List;

public interface ScheduleService {

    List<ScheduleDto> findSchedulesDtoByTrainId(int id);

    List<ScheduleDtoRest> findScheduleByStationId(int stationId);

    void deleteTrainFromSchedule(int trainId, int stationId);

}
