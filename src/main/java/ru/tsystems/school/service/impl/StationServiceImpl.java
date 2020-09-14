package ru.tsystems.school.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.school.dao.StationDao;
import ru.tsystems.school.dao.TrainDao;
import ru.tsystems.school.model.dto.ScheduleDto;
import ru.tsystems.school.model.dto.StationDto;
import ru.tsystems.school.model.dto.TrainDto;
import ru.tsystems.school.model.entity.Station;
import ru.tsystems.school.model.entity.Train;
import ru.tsystems.school.service.StationService;
import ru.tsystems.school.model.mapper.*;
import ru.tsystems.school.service.exceptions.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Log4j
public class StationServiceImpl implements StationService {

    private final StationDao stationDao;
    private final StationMapper stationMapper;
    private final TrainMapper trainMapper;
    private final ScheduleMapper scheduleMapper;
    private final TrainDao trainDao;
    private final JmsTemplate jmsTemplate;

    @Override
    public void save(StationDto stationDto) {

        if (stationDto.getName().isEmpty()) {
            throw new RuntimeException("Station name is empty");
        }

        List<Station> stations = stationDao.findAll();
        if (!stations.isEmpty()) {
            for (Station station : stations) {
                if (stationDto.getName().equals(station.getName())) {
                    throw new NotUniqueNameException("Station with such name already exists");
                }
            }
        }
        log.info("Station " + stationDto.getName() + " created");
        stationDao.save(stationMapper.toEntity(stationDto));
        jmsTemplate.send(session -> session.createTextMessage("station created"));
    }

    @Override
    public void deleteStationById(int id) {

        Station byId = stationDao.findById(id);
        stationDao.deleteById(id);
        log.info("Station " + byId.getName() + " was deleted");
        jmsTemplate.send(session -> session.createTextMessage("station was deleted"));

    }

    @Override
    public List<TrainDto> findAllTrainsForCurrentStation(int id) {

        List<Train> allTrains = stationDao.findAllTrainsForCurrentStation(id);
        return allTrains.stream().map(trainMapper::toDto).collect(Collectors.toList());

    }

    @Override
    public List<StationDto> findAllStations() {

        return stationDao.findAll().stream().map(stationMapper::toDto).collect(Collectors.toList());

    }

    @Override
    public StationDto findStationById(int id) {

        Station station = stationDao.findById(id);
        if (station == null) {
            throw new NoSuchEntityException("No station found");
        }
        return stationMapper.toDto(stationDao.findById(id));

    }

    @Override
    public void saveSchedule(ScheduleDto schedule) {

        stationDao.saveSchedule(scheduleMapper.toEntity(schedule));
        log.info("schedule for " + schedule.getStation().getName() + " station was created");
        jmsTemplate.send(session -> session.createTextMessage("schedule added"));

    }

    @Override
    public List<ScheduleDto> findAllSchedulesForStation(int id) {
        return stationDao.findScheduleForStation(id)
                .stream()
                .map(scheduleMapper::toDto)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainDto> findSuitableTrains(StationDto from, StationDto to, String fromTime, String toTime) {

        if (from.getName().equals(to.getName())) {
            throw new RuntimeException("You have to choose different stations");
        }

        Station stationFromEntity = stationMapper.toEntity(from);
        Station stationToEntity = stationMapper.toEntity(to);

        return stationDao.findSuitableTrains(stationFromEntity, stationToEntity, fromTime, toTime)
                .stream()
                .map(trainMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void update(StationDto stationDto) {

        Station station = stationDao.findById(stationDto.getId());
        station.setName(stationDto.getName());
        stationDao.update(station);
        log.info("station: " + station.getName() + " was updated");
        jmsTemplate.send(session -> session.createTextMessage("station updated"));

    }

    @Override
    public void update(int id, StationDto stationDto) {

        Station byId = stationDao.findById(id);
        byId.setName(stationDto.getName());
        stationDao.update(id, stationMapper.toEntity(stationDto));
        log.info("station: " + stationDto.getName() + " was updated");
        jmsTemplate.send(session -> session.createTextMessage("station updated"));

    }

    @Override
    public StationDto findByStationName(String name) {
        Station stationFromDb = stationDao.findByStationName(name);
        return stationMapper.toDto(stationFromDb);
    }

    @Override
    public List<ScheduleDto> findScheduleForStationAndTrain(int stationId, int trainId) {
        return stationDao.findScheduleForStationAndTrain(stationId, trainId)
                .stream()
                .map(scheduleMapper::toDto)
                .sorted()
                .collect(Collectors.toList());
    }


    @Override
    public List<TrainDto> potentialTrainsForStation(int stationId) {

        List<TrainDto> allTrains = trainDao.findAll()
                .stream()
                .map(trainMapper::toDto)
                .collect(Collectors.toList());

        List<TrainDto> allTrainsForStation = findAllTrainsForCurrentStation(stationId);

        List<TrainDto> resultTrainsForStation = new ArrayList<>();

        for (TrainDto trainDto : allTrains) {
            boolean temp = false;
            for (TrainDto trainForStation : allTrainsForStation) {
                if (trainDto.getId() == trainForStation.getId()) {
                    temp = true;
                    break;
                }
            }
            if (!temp)
                resultTrainsForStation.add(trainDto);
        }

        return resultTrainsForStation;
    }

    @Override
    public void addTrainToStation(String departureTime, String arrivalTime, String train, StationDto stationDto,
                                  int id) {

        StationDto station = findStationById(id);
        ScheduleDto scheduleDto = new ScheduleDto();
        Train train1 = trainDao.findByTrainNumber(train).get(0);
        TrainDto trainDto = trainMapper.toDto(train1);
        LocalTime departureTimeLocalTime = LocalTime.parse(departureTime);
        LocalTime arrivalTimeLocalTime = LocalTime.parse(arrivalTime);
        scheduleDto.setDepartureTime(departureTimeLocalTime);
        scheduleDto.setArrivalTime(arrivalTimeLocalTime);
        scheduleDto.setTrain(trainDto);
        scheduleDto.setStation(station);
        saveSchedule(scheduleDto);

        log.info("train " + trainDto.getTrainNumber() + " was added to " + station.getName() + " station");
        jmsTemplate.send(session -> session.createTextMessage("train was added to the station"));
    }
}
