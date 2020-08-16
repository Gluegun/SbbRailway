package ru.tsystems.school.mapper.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.school.dao.PassengerDao;
import ru.tsystems.school.dao.TrainDao;
import ru.tsystems.school.dto.PassengerDto;
import ru.tsystems.school.dto.TicketDto;
import ru.tsystems.school.mapper.PassengerMapper;
import ru.tsystems.school.mapper.TicketMapper;
import ru.tsystems.school.mapper.TrainMapper;
import ru.tsystems.school.model.Passenger;
import ru.tsystems.school.model.Ticket;
import ru.tsystems.school.model.Train;

@Component
@Transactional
@AllArgsConstructor
public class TicketMapperImpl implements TicketMapper {

    private final PassengerMapper passengerMapper;
    private final TrainMapper trainMapper;
    private final TrainDao trainDao;
    private final PassengerDao passengerDao;

    public TicketDto toDto(Ticket ticket) {

        TicketDto ticketDto = new TicketDto();
        if (ticket != null) {
            ticketDto.setId(ticket.getId());
            ticketDto.setDepartureTime(ticket.getDepartureTime());
            ticketDto.setPassenger(passengerMapper.toDto(ticket.getPassenger()));
            ticketDto.setTrain(trainMapper.toDto(ticket.getTrain()));
        }

        return ticketDto;
    }

    public Ticket toEntity(TicketDto ticketDto) {

        int trainDtoId = ticketDto.getTrain().getId();
        Train train = trainDao.findById(trainDtoId);

        int passengerId = ticketDto.getPassenger().getId();
        Passenger passenger = passengerDao.findById(passengerId);

        Ticket ticket = new Ticket();
        ticket.setId(ticketDto.getId());
        ticket.setDepartureTime(ticketDto.getDepartureTime());
        ticket.setTrain(train);
        ticket.setPassenger(passenger);

        return ticket;
    }
}
