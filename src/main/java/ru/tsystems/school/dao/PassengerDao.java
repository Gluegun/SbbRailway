package ru.tsystems.school.dao;

import ru.tsystems.school.model.Passenger;

import java.util.List;

public interface PassengerDao {

    List<Passenger> findAll();

    Passenger findById(int id);

    void save(Passenger passenger);

    void deleteById(int id);

    Passenger findByUserName(String userName);

}
