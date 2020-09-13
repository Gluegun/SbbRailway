package ru.tsystems.school.dao.impl;

import org.springframework.stereotype.Repository;
import ru.tsystems.school.dao.AbstractJpaDao;
import ru.tsystems.school.dao.TicketDao;
import ru.tsystems.school.model.entity.Station;
import ru.tsystems.school.model.entity.Ticket;

import java.time.LocalTime;
import java.util.List;

@Repository
public class TicketDaoImpl extends AbstractJpaDao<Ticket> implements TicketDao {

    public TicketDaoImpl() {
        super();
        setClazz(Ticket.class);
    }

    @Override
    public List<Ticket> findAll() {

        return getEntityManager().createQuery("select s from Ticket s", Ticket.class).getResultList();
    }

    @Override
    public Ticket findById(int id) {
        return getEntityManager().createQuery("select s from Ticket s where s.id =:id", Ticket.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Station findStationFromByTrainIdAndDepartureTime(int trainId, LocalTime departureTime) {

        return getEntityManager().createQuery("select sc.station from Schedule sc where sc.train.id = :trainId and " +
                "sc.departureTime = :depTime", Station.class)
                .setParameter("trainId", trainId)
                .setParameter("depTime", departureTime)
                .getSingleResult();

    }

    @Override
    public List<Ticket> findTicketsByPassengerId(int id) {

        return getEntityManager().createQuery(
                "select t from Ticket t where t.passenger.id =:id", Ticket.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<Ticket> findTicketsByTrainId(int id) {

        return getEntityManager().createQuery(
                "select t from Ticket t where t.train.id =:id", Ticket.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public void saveTicket(Ticket ticket) {

        getEntityManager().createNativeQuery(
                "insert into tickets (id, departureTime, passenger_id, train_id) " +
                        "values (:id, :departureTime, :passengerId, :trainId);"
        )
                .setParameter("id", ticket.getId())
                .setParameter("departureTime", ticket.getDepartureTime())
                .setParameter("passengerId", ticket.getPassenger().getId())
                .setParameter("trainId", ticket.getTrain().getId())
                .executeUpdate();

    }
}
