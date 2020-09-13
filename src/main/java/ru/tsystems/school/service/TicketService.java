package ru.tsystems.school.service;

import ru.tsystems.school.dto.StationDto;
import ru.tsystems.school.dto.TicketDto;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public interface TicketService {

    TicketDto findById(int id);

    void save(TicketDto ticketDto);

    void deleteById(int id);

    List<TicketDto> findTicketsByPassengerId(int id);

    List<TicketDto> findTicketsByTrainId(int id);

    List<TicketDto> getTicketsForAuthorizedUser();

    void buyTicket(int trainId, String fromStation);

    Map<Integer, Boolean> ticketsForPassengers(String fromStation, String toStation, String fromTime, String toTime);

    StationDto getStationDeparture(int trainId, LocalTime departureTime);

}
