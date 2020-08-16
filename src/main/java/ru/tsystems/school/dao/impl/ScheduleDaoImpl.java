package ru.tsystems.school.dao.impl;

import org.springframework.stereotype.Repository;
import ru.tsystems.school.dao.AbstractJpaDao;
import ru.tsystems.school.dao.ScheduleDao;
import ru.tsystems.school.model.Schedule;

import java.util.List;

@Repository
public class ScheduleDaoImpl extends AbstractJpaDao<Schedule> implements ScheduleDao {

    public ScheduleDaoImpl() {
        setClazz(Schedule.class);
    }

    @Override
    public List<Schedule> findAll() {

        return getEntityManager()
                .createQuery("select s from Schedule s", Schedule.class)
                .getResultList();
    }

    @Override
    public List<Schedule> findSchedulesByTrainId(int id) {

        return getEntityManager().createQuery(
                "select t from Schedule t where t.train.id =:id", Schedule.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<Schedule> findSchedulesByStationId(int id) {

        return getEntityManager().createQuery(
                "select t from Schedule t where t.station.id =:id", Schedule.class)
                .setParameter("id", id)
                .getResultList();
    }
}
