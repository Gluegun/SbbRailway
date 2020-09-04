package ru.tsystems.school.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.school.dao.StationDao;
import ru.tsystems.school.dao.TrainDao;
import ru.tsystems.school.dto.ScheduleDto;
import ru.tsystems.school.dto.StationDto;
import ru.tsystems.school.dto.TrainDto;
import ru.tsystems.school.exceptions.NoSuchEntityException;
import ru.tsystems.school.exceptions.NotUniqueNameException;
import ru.tsystems.school.mapper.ScheduleMapper;
import ru.tsystems.school.mapper.StationMapper;
import ru.tsystems.school.mapper.TrainMapper;
import ru.tsystems.school.model.Station;
import ru.tsystems.school.model.Train;
import ru.tsystems.school.service.StationService;

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

    @Override
    public void save(StationDto stationDto) {

        List<Station> stations = stationDao.findAll();
        for (Station station : stations) {
            if (stationDto.getName().equals(station.getName())) {
                throw new NotUniqueNameException("Station with such name already exists");
            }
        }
        stationDao.save(stationMapper.toEntity(stationDto));
    }

    @Override
    public void deleteStationById(int id) {

        stationDao.deleteById(id);
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
    }

    @Override
    public List<ScheduleDto> findAllSchedulesForStation(int id) {
        return stationDao.findScheduleForStation(id)
                .stream()
                .map(scheduleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainDto> findSuitableTrains(StationDto from, StationDto to, String fromTime, String toTime) {

        Station stationFromEntity = stationMapper.toEntity(from);
        Station stationToEntity = stationMapper.toEntity(to);

        return stationDao.findSuitableTrains(stationFromEntity, stationToEntity, fromTime, toTime)
                .stream()
                .map(trainMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void update(StationDto stationDto) {

        log.info("station has: " + stationDto.getName() + " name before");
        Station station = stationDao.findById(stationDto.getId());
        station.setName(stationDto.getName());
        stationDao.update(station);
        log.info("now it has: " + station.getName());

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
    public void addTrainToStation(String departureTime, String train, StationDto stationDto,
                                  int id) {

        StationDto station = findStationById(id);
        ScheduleDto scheduleDto = new ScheduleDto();
        Train train1 = trainDao.findByTrainNumber(train).get(0);
        TrainDto trainDto = trainMapper.toDto(train1);
        LocalTime departureTimeLocalTime = LocalTime.parse(departureTime);
        scheduleDto.setDepartureTime(departureTimeLocalTime);
        scheduleDto.setTrain(trainDto);
        scheduleDto.setStation(station);
        saveSchedule(scheduleDto);
    }
}
