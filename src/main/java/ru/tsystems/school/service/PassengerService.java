package ru.tsystems.school.service;

import ru.tsystems.school.dto.PassengerDto;
import ru.tsystems.school.dto.TicketDto;
import ru.tsystems.school.model.Passenger;
import ru.tsystems.school.model.Train;

import java.util.List;
import java.util.Map;

public interface PassengerService {

    List<PassengerDto> findAllDtoPassengers();

    PassengerDto findById(int id);

    void save(PassengerDto passengerDto);

    void delete(int id);

    void createUser(PassengerDto passengerDto);

    PassengerDto findByUserName(String userName);

    PassengerDto getAuthorizedPassenger();

}
