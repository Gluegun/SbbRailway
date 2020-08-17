package ru.tsystems.school.dao.impl;

import org.springframework.stereotype.Repository;
import ru.tsystems.school.dao.AbstractJpaDao;
import ru.tsystems.school.dao.ScheduleDao;
import ru.tsystems.school.dao.TrainDao;
import ru.tsystems.school.model.*;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TrainDaoImpl extends AbstractJpaDao<Train> implements TrainDao {

    private final ScheduleDao scheduleDao;

    public TrainDaoImpl(ScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
        setClazz(Train.class);
    }

    @Override
    public List<Train> findAll() {

        return getEntityManager().createQuery("select s from Train s", Train.class).getResultList();
    }

    @Override
    public List<Station> findAllStations(int id) {

        List<Schedule> stationsForTrain = getEntityManager().createQuery(
                "select s from Schedule s where s.train.id =:id", Schedule.class)
                .setParameter("id", id)
                .getResultList();

        return stationsForTrain.stream().map(Schedule::getStation).collect(Collectors.toList());
    }

    @Override
    public List<Passenger> findAllPassengers(int id) {

        List<Ticket> ticketsForCurrentTrain = getEntityManager().createQuery(
                "select t from Ticket t where t.train.id =:id", Ticket.class)
                .setParameter("id", id)
                .getResultList();

        return ticketsForCurrentTrain.stream().map(Ticket::getPassenger).collect(Collectors.toList());
    }

    @Override
    public List<Train> findByTrainNumber(String number) {

        return getEntityManager().createQuery(
                "select t from Train t where t.trainNumber like :number", Train.class
        )
                .setParameter("number", "%" + number + "%")
                .getResultList();
    }

    @Override
    public void deleteById(int id) {

        List<Schedule> schedules = scheduleDao.findSchedulesByTrainId(id);
        for (Schedule schedule : schedules)
            getEntityManager().remove(schedule);

        Train train = findById(id);
        getEntityManager().remove(train);

    }
}
