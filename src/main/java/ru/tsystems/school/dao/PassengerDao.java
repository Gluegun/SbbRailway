package ru.tsystems.school.dao;

import ru.tsystems.school.model.entity.Passenger;

public interface PassengerDao {

    Passenger findById(int id);

    Passenger findByUserName(String userName);

}
