package ru.tsystems.school.dao.impl;

import org.springframework.stereotype.Repository;
import ru.tsystems.school.dao.AbstractJpaDao;
import ru.tsystems.school.dao.ScheduleDao;
import ru.tsystems.school.dao.StationDao;
import ru.tsystems.school.dao.TrainDao;
import ru.tsystems.school.model.entity.Schedule;
import ru.tsystems.school.model.entity.Station;
import ru.tsystems.school.model.entity.Train;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StationDaoImpl extends AbstractJpaDao<Station> implements StationDao {

    private final ScheduleDao scheduleDao;
    private final TrainDao trainDao;

    public StationDaoImpl(ScheduleDao scheduleDao, TrainDao trainDao) {
        setClazz(Station.class);
        this.trainDao = trainDao;
        this.scheduleDao = scheduleDao;
    }

    @Override
    public List<Train> findAllTrainsForCurrentStation(int id) {

        List<Schedule> stationsForTrain =
                getEntityManager().createQuery(
                        "select s from Schedule s where s.station.id =:id", Schedule.class)
                        .setParameter("id", id)
                        .getResultList();

        return stationsForTrain.stream().map(Schedule::getTrain).collect(Collectors.toList());
    }

    @Override
    public List<Schedule> findScheduleForStation(int id) {

        return getEntityManager().createQuery(
                "select s from Schedule s where s.station.id=:id", Schedule.class
        ).setParameter("id", id).getResultList();

    }

    @Override
    public List<Schedule> findScheduleForStationAndTrain(int stationId, int trainId) {

        return getEntityManager()
                .createQuery(
                        "select s from Schedule s where s.station.id=:stationId and s.train.id=:trainId",
                        Schedule.class)
                .setParameter("stationId", stationId)
                .setParameter("trainId", trainId)
                .getResultList();

    }

    @Override
    public void update(int id, Station station) {

        Station byId = findById(id);

        getEntityManager().createQuery("update Station s set s.name=:name, s.id=:id where s.id=:dbStationId")
                .setParameter("name", station.getName())
                .setParameter("id", station.getId())
                .setParameter("dbStationId", byId.getId())
                .executeUpdate();


    }

    @Override
    public void addSchedule(int stationId, int trainId, LocalTime arrivalTime, LocalTime departureTime) {

        Station stationFromDb = findById(stationId);
        Train trainFromDb = trainDao.findById(trainId);
        Schedule schedule = new Schedule(trainFromDb, arrivalTime, departureTime, stationFromDb);
        scheduleDao.save(schedule);

    }

    @Override
    public List<Train> findSuitableTrains(Station from, Station to, String fromTime, String toTime) {

        List resultList = getEntityManager().createNativeQuery("select train_id from schedule where " +
                "station_id =:fromId and departure_time <= :fromTime " +
                "union select train_id from schedule where station_id = :toId and arrival_time <= :toTime")
                .setParameter("fromId", from.getId())
                .setParameter("fromTime", fromTime)
                .setParameter("toId", to.getId())
                .setParameter("toTime", toTime)
                .getResultList();

        List<Train> trains = new ArrayList<>();

        for (Object o : resultList) {

            Train byId = trainDao.findById((Integer) o);
            trains.add(byId);
        }

        return trains;

    }

    @Override
    public Station findByStationName(String name) {

        return getEntityManager()
                .createQuery("select t from Station t where t.name= :name", Station.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public void saveSchedule(Schedule schedule) {
        scheduleDao.save(schedule);
    }

    @Override
    public void deleteById(int id) {

        List<Schedule> schedules = scheduleDao.findSchedulesByStationId(id);
        for (Schedule schedule : schedules) {
            getEntityManager().remove(schedule);
        }

        Station station = findById(id);
        getEntityManager().remove(station);

    }
}
