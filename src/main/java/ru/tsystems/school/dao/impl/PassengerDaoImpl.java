package ru.tsystems.school.dao.impl;

import org.springframework.stereotype.Component;
import ru.tsystems.school.dao.AbstractJpaDao;
import ru.tsystems.school.dao.PassengerDao;
import ru.tsystems.school.model.entity.Passenger;

import javax.persistence.NoResultException;


@Component
public class PassengerDaoImpl extends AbstractJpaDao<Passenger> implements PassengerDao {

    public PassengerDaoImpl() {
        super();
        setClazz(Passenger.class);
    }

    @Override
    public Passenger findByUserName(String userName) {

        Passenger passenger = null;

        try {
            passenger = getEntityManager()
                    .createQuery("select p from Passenger p  where p.usrName=:userName",
                            Passenger.class)
                    .setParameter("userName", userName)
                    .getSingleResult();
        } catch (NoResultException exception) {
            exception.getMessage();
        }

        return passenger;
    }
}
