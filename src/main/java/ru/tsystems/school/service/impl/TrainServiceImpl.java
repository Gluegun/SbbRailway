package ru.tsystems.school.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.school.dao.TrainDao;
import ru.tsystems.school.model.dto.PassengerDto;
import ru.tsystems.school.model.dto.ScheduleDto;
import ru.tsystems.school.model.dto.StationDto;
import ru.tsystems.school.model.dto.TrainDto;
import ru.tsystems.school.service.exceptions.CantDeleteException;
import ru.tsystems.school.service.exceptions.NoSuchEntityException;
import ru.tsystems.school.service.exceptions.NotUniqueNameException;
import ru.tsystems.school.model.mapper.PassengerMapper;
import ru.tsystems.school.model.mapper.StationMapper;
import ru.tsystems.school.model.mapper.TrainMapper;
import ru.tsystems.school.model.entity.Train;
import ru.tsystems.school.service.StationService;
import ru.tsystems.school.service.TrainService;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Log4j
public class TrainServiceImpl implements TrainService {

    private final TrainDao trainDao;
    private final PassengerMapper passengerMapper;
    private final StationMapper stationMapper;
    private final TrainMapper trainMapper;
    private final StationService stationService;
    private final JmsTemplate jmsTemplate;

    @Override
    public List<TrainDto> findAllDtoTrains() {

        return trainDao.findAll()
                .stream()
                .map(trainMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TrainDto findTrainById(int id) {

        Train train = trainDao.findById(id);
        if (train == null) {
            throw new NoSuchEntityException("No train found");
        }
        return trainMapper.toDto(trainDao.findById(id));
    }

    @Override
    public void save(TrainDto trainDto) {

        if (trainDto.getTrainNumber().trim().isEmpty()) {
            throw new RuntimeException("Train should have train number!");
        }

        List<Train> trains = trainDao.findAll();
        for (Train train : trains) {
            if (trainDto.getTrainNumber().equals(train.getTrainNumber())) {
                throw new NotUniqueNameException("Train has already exists");
            }
        }
        trainDao.save(trainMapper.toEntity(trainDto));
        log.info("train" + trainDto.getTrainNumber() + " was created");
        jmsTemplate.send(session -> session.createTextMessage("train created"));

    }

    @Override
    public void deleteById(int id) {

        Train train = trainDao.findById(id);
        if (!train.getPassengers().isEmpty()) {
            throw new CantDeleteException("Train is not empty!");
        } else trainDao.deleteById(id);
        log.info("train " + train.getTrainNumber() + " was deleted");
        jmsTemplate.send(session -> session.createTextMessage("train deleted"));

    }

    @Override
    public List<StationDto> findAllStations(int id) {
        return trainDao.findAllStations(id)
                .stream()
                .map(stationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PassengerDto> findAllPassengersForTrain(int trainId) {
        return trainDao.findAllPassengers(trainId)
                .stream()
                .map(passengerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void update(TrainDto trainDto) {

        Train train = trainDao.findById(trainDto.getId());
        train.setSeatsAmount(trainDto.getSeatsAmount());
        train.setTrainNumber(trainDto.getTrainNumber());
        trainDao.update(train);
        log.info("train " + train.getTrainNumber() + " was updated to " + train.getTrainNumber());
        jmsTemplate.send(session -> session.createTextMessage("train updated"));

    }


    @Override
    public void addStationToTrain(String departureTime, String arrivalTime,
                                  String station, TrainDto trainDto, int trainId) {


        TrainDto train = findTrainById(trainId);
        StationDto stationFoundByName = stationService.findByStationName(station);
        ScheduleDto scheduleDto = new ScheduleDto();
        LocalTime departureTimeLocalTime = LocalTime.parse(departureTime);
        LocalTime arrivalTimeLocalTime = LocalTime.parse(arrivalTime);
        scheduleDto.setDepartureTime(departureTimeLocalTime);
        scheduleDto.setArrivalTime(arrivalTimeLocalTime);
        scheduleDto.setTrain(train);
        scheduleDto.setStation(stationFoundByName);
        stationService.saveSchedule(scheduleDto);
        log.info("station " + stationFoundByName.getName() + " was added to train "
                + train.getTrainNumber() + " route");
        jmsTemplate.send(session -> session.createTextMessage("station added to train"));

    }

    @Override
    public List<StationDto> potentialStationsForTrain(int trainId) {

        List<StationDto> allStations = stationService.findAllStations();
        List<StationDto> allStationsForTrain = findAllStations(trainId);
        List<StationDto> resultStationDtoForTrain = new ArrayList<>();

        for (StationDto stationDto : allStations) {
            boolean temp = false;
            for (StationDto stationForTrain : allStationsForTrain) {
                if (stationDto.getId() == stationForTrain.getId()) {
                    temp = true;
                    break;
                }
            }
            if (!temp)
                resultStationDtoForTrain.add(stationDto);
        }


        return resultStationDtoForTrain;
    }
}
