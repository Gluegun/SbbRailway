package ru.tsystems.school.service;

import org.springframework.web.bind.annotation.RequestParam;
import ru.tsystems.school.dto.TicketDto;
import ru.tsystems.school.model.Ticket;

import java.util.List;
import java.util.Map;

public interface TicketService {

    List<TicketDto> findAll();

    TicketDto findById(int id);

    void save(TicketDto ticketDto);

    void deleteById(int id);

    List<TicketDto> findTicketsByPassengerId(int id);

    List<TicketDto> findTicketsByTrainId(int id);

    List<TicketDto> getTicketsForAuthorizedUser();

    void buyTicket(int trainId, String fromStation);

    Map<Integer, Boolean> ticketsForPassengers(String fromStation, String toStation, String fromTime, String toTime);

}
