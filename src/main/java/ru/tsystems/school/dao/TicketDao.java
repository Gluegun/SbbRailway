package ru.tsystems.school.dao;

import ru.tsystems.school.model.entity.Station;
import ru.tsystems.school.model.entity.Ticket;

import java.time.LocalTime;
import java.util.List;

public interface TicketDao {

    List<Ticket> findAll();

    Ticket findById(int id);

    void saveTicket(Ticket ticket);

    void deleteById(int id);

    List<Ticket> findTicketsByPassengerId(int id);

    List<Ticket> findTicketsByTrainId(int id);

    public Station findStationFromByTrainIdAndDepartureTime(int trainId, LocalTime departureTime);


}
