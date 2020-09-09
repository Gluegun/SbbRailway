package ru.tsystems.school.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.school.dao.ScheduleDao;
import ru.tsystems.school.dto.ScheduleDto;
import ru.tsystems.school.mapper.ScheduleMapper;
import ru.tsystems.school.model.Schedule;
import ru.tsystems.school.service.ScheduleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleDao scheduleDao;
    private final ScheduleMapper scheduleMapper;

    @Override
    public List<ScheduleDto> findAll() {
        return scheduleDao.findAll().stream().map(scheduleMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDto> findSchedulesDtoByTrainId(int id) {

        return scheduleDao.findSchedulesByTrainId(id)
                .stream()
                .map(scheduleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDto> findScheduleByStationId(int stationId) {
        List<Schedule> schedulesByStationId = scheduleDao.findSchedulesByStationId(stationId);
        return schedulesByStationId.stream()
                .map(scheduleMapper::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public void deleteById(int id) {
        scheduleDao.deleteById(id);
    }

    @Override
    public void deleteTrainFromSchedule(int stationId, int trainId) {

        scheduleDao.deleteTrainFromSchedule(stationId, trainId);

    }
}
