package ru.tsystems.school.dao;

import ru.tsystems.school.model.Passenger;
import ru.tsystems.school.model.Station;
import ru.tsystems.school.model.Train;

import java.util.List;

public interface TrainDao {

    List<Train> findAll();

    Train findById(int id);

    void save(Train train);

    void deleteById(int id);

    void update(Train train);

    List<Station> findAllStations(int id);

    List<Passenger> findAllPassengers(int id);

    List<Train> findByTrainNumber(String number);

}
