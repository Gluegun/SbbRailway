package ru.tsystems.school.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.school.dao.TicketDao;
import ru.tsystems.school.dto.*;
import ru.tsystems.school.exceptions.CantBuyTicketException;
import ru.tsystems.school.mapper.StationMapper;
import ru.tsystems.school.mapper.TicketMapper;
import ru.tsystems.school.model.Ticket;
import ru.tsystems.school.service.PassengerService;
import ru.tsystems.school.service.StationService;
import ru.tsystems.school.service.TicketService;
import ru.tsystems.school.service.TrainService;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketDao ticketDao;
    private final TicketMapper ticketMapper;
    private final PassengerService passengerService;
    private final TrainService trainService;
    private final StationService stationService;
    private final StationMapper stationMapper;

    @Override
    public List<TicketDto> findAll() {
        return ticketDao.findAll().stream().map(ticketMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public TicketDto findById(int id) {
        return ticketMapper.toDto(ticketDao.findById(id));
    }

    @Override
    public void save(TicketDto ticketDto) {

        Ticket ticket = ticketMapper.toEntity(ticketDto);
        ticketDao.saveTicket(ticket);
    }

    @Override
    public List<TicketDto> findTicketsByPassengerId(int id) {

        return ticketDao.findTicketsByPassengerId(id)
                .stream()
                .map(ticketMapper::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public List<TicketDto> findTicketsByTrainId(int id) {

        return ticketDao.findTicketsByTrainId(id)
                .stream()
                .map(ticketMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(int id) {

        ticketDao.deleteById(id);
    }

    @Override
    public List<TicketDto> getTicketsForAuthorizedUser() {

        List<TicketDto> tickets = new ArrayList<>();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getName().equals("anonymousUser")) {
            PassengerDto passenger = passengerService.findByUserName(auth.getName());
            tickets = findTicketsByPassengerId(passenger.getId());
        }
        return tickets;
    }

    @Override
    public StationDto getStationDeparture(int trainId, LocalTime departureTime) {

       return stationMapper.toDto(ticketDao.findStationFromByTrainIdAndDepartureTime(trainId, departureTime));

    }

    @Override
    public void buyTicket(int trainId, String fromStation) {

        PassengerDto passenger = passengerService.getAuthorizedPassenger();
        TrainDto trainDtoById = trainService.findTrainById(trainId);

        List<Ticket> ticketsByTrainId = ticketDao.findTicketsByTrainId(trainId);

        for (Ticket ticket : ticketsByTrainId) {
            if (ticket.getPassenger().getUsername().equals(passenger.getUsername())) {
                throw new CantBuyTicketException("Passenger has already bought this ticket");
            }
        }

        if (trainDtoById.getSeatsAmount() == 0) {
            throw new CantBuyTicketException("Train is already full, no free seats");
        }

        StationDto from = stationService.findByStationName(fromStation);

        List<ScheduleDto> scheduleForStationAndTrain =
                stationService.findScheduleForStationAndTrain(from.getId(), trainDtoById.getId());

        LocalTime departureTime = null;

        for (ScheduleDto scheduleDto : scheduleForStationAndTrain) {
            departureTime = scheduleDto.getDepartureTime();
        }

        int lastTicketId = getLastTicketId();

        TicketDto ticket = new TicketDto();
        ticket.setId(lastTicketId + 1);
        ticket.setDepartureTime(departureTime);
        ticket.setPassenger(passenger);
        trainDtoById.setSeatsAmount(trainDtoById.getSeatsAmount() - 1);

        trainService.update(trainDtoById);
        TrainDto trainDto = trainService.findTrainById(trainDtoById.getId());

        ticket.setTrain(trainDto);

        save(ticket);

    }

    private int getLastTicketId() {

        int id = 0;
        List<TicketDto> tickets = findAll();
        for (TicketDto ticket : tickets) {
            id += ticket.getId();
        }
        return id;
    }

    @Override
    public Map<Integer, Boolean> ticketsForPassengers(String fromStation, String toStation,
                                                      String fromTime, String toTime) {

        PassengerDto passenger = passengerService.getAuthorizedPassenger();

        Map<Integer, Boolean> ticketsPassenger = new HashMap<>();
        List<TicketDto> tickets;

        StationDto from = stationService.findByStationName(fromStation);
        StationDto to = stationService.findByStationName(toStation);

        List<TrainDto> trains = stationService.findSuitableTrains(from, to, fromTime, toTime);

        for (TrainDto train : trains) {
            tickets = findTicketsByTrainId(train.getId());

            for (TicketDto ticket : tickets) {
                if (ticket.getPassenger().getId() == passenger.getId())
                    ticketsPassenger.put(train.getId(), true);
            }
        }

        return ticketsPassenger;

    }
}
