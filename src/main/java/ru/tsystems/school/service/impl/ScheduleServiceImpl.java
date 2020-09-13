package ru.tsystems.school.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.school.dao.ScheduleDao;
import ru.tsystems.school.dto.ScheduleDto;
import ru.tsystems.school.dto.ScheduleDtoRest;
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
    private final JmsTemplate jmsTemplate;

    @Override
    public List<ScheduleDto> findAll() {
        return scheduleDao.findAll().stream().map(scheduleMapper::toDto).sorted().collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDto> findSchedulesDtoByTrainId(int id) {

        return scheduleDao.findSchedulesByTrainId(id)
                .stream()
                .map(scheduleMapper::toDto)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDtoRest> findScheduleByStationId(int stationId) {

        List<Schedule> schedulesByStationId = scheduleDao.findSchedulesByStationId(stationId);
        List<ScheduleDto> schedules = schedulesByStationId.stream()
                .map(scheduleMapper::toDto).sorted().collect(Collectors.toList());

        return schedules.stream().map(ScheduleDtoRest::new).collect(Collectors.toList());

    }

    @Override
    public void deleteById(int id) {
        scheduleDao.deleteById(id);
        jmsTemplate.send(session -> session.createTextMessage("schedule deleted"));
    }

    @Override
    public void deleteTrainFromSchedule(int stationId, int trainId) {

        scheduleDao.deleteTrainFromSchedule(stationId, trainId);
        jmsTemplate.send(session -> session.createTextMessage("schedule was deleted"));

    }

    @Override
    public void delayTrain(int trainId, int stationId, int minutesAmount) {

        scheduleDao.delayTrain(trainId, stationId, minutesAmount);
        jmsTemplate.send(session -> session.createTextMessage("train delayed"));

    }
}
