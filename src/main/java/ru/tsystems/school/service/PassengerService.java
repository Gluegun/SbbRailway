package ru.tsystems.school.service;

import ru.tsystems.school.dto.PassengerDto;

import java.util.List;

public interface PassengerService {

    List<PassengerDto> findAllDtoPassengers();

    PassengerDto findById(int id);

    void save(PassengerDto passengerDto);

    void delete(int id);

    void createUser(PassengerDto passengerDto);

    PassengerDto findByUserName(String userName);

    PassengerDto getAuthorizedPassenger();

}
