package ru.tsystems.school.dao.impl;

import org.springframework.stereotype.Repository;
import ru.tsystems.school.dao.AbstractJpaDao;
import ru.tsystems.school.dao.ScheduleDao;
import ru.tsystems.school.dao.StationDao;
import ru.tsystems.school.dao.TrainDao;
import ru.tsystems.school.model.Schedule;
import ru.tsystems.school.model.Station;
import ru.tsystems.school.model.Train;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    public void addSchedule(int stationId, int trainId, LocalTime arrivalTime) {

        try {

            Station stationFromDb = findById(stationId);
            if (stationFromDb == null) {
                return;
            }

            Train trainFromDb = trainDao.findById(trainId);
            if (trainFromDb == null) {
                return;
            }

            Schedule schedule = new Schedule(trainFromDb, arrivalTime, stationFromDb);

            scheduleDao.save(schedule);

        } catch (Exception ex) {

        }
    }

    @Override
    public List<Train> findSuitableTrains(Station from, Station to, String fromTime, String toTime) {


        List resultList = getEntityManager().createNativeQuery(
                "select train_id from schedule where schedule.station_id = :from or " +
                        "schedule.station_id =:to and schedule.departure_time between " +
                        ":fromTime and :toTime")
                .setParameter("from", from.getId())
                .setParameter("to", to.getId())
                .setParameter("fromTime", fromTime)
                .setParameter("toTime", toTime)
                .getResultList();

        Set<Train> trainsSet = new HashSet<>();

        for (Object o : resultList) {
            trainsSet.add(trainDao.findById((Integer) o));
        }

        return new ArrayList<>(trainsSet);

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
