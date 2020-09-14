package ru.tsystems.school.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.school.dao.ScheduleDao;
import ru.tsystems.school.dao.TrainDao;
import ru.tsystems.school.model.dto.ScheduleDto;
import ru.tsystems.school.model.dto.ScheduleDtoRest;
import ru.tsystems.school.model.entity.Schedule;
import ru.tsystems.school.model.entity.Train;
import ru.tsystems.school.service.ScheduleService;
import ru.tsystems.school.model.mapper.*;
import ru.tsystems.school.service.exceptions.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Log4j
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleDao scheduleDao;
    private final ScheduleMapper scheduleMapper;
    private final JmsTemplate jmsTemplate;
    private final TrainDao trainDao;


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
    public void deleteTrainFromSchedule(int trainId, int stationId) {

        Train train = trainDao.findById(trainId);
        if (!train.getPassengers().isEmpty()) {
            throw new CantDeleteException("train is not empty, you can't modify route");
        }

        scheduleDao.deleteTrainFromSchedule(trainId, stationId);
        jmsTemplate.send(session -> session.createTextMessage("schedule was deleted"));

    }
}
