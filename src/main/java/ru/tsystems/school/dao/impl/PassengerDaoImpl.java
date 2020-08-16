package ru.tsystems.school.dao.impl;

import org.springframework.stereotype.Component;
import ru.tsystems.school.dao.AbstractJpaDao;
import ru.tsystems.school.dao.PassengerDao;
import ru.tsystems.school.model.Passenger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Component
public class PassengerDaoImpl extends AbstractJpaDao<Passenger> implements PassengerDao {

    public PassengerDaoImpl() {
        super();
        setClazz(Passenger.class);
    }

    @Override
    public Passenger findByUserName(String userName) {

        List<Passenger> passenger = null;

        try {
            passenger = getEntityManager().createQuery("select p from Passenger p  where p.userName=:userName",
                    Passenger.class)
                    .setParameter("userName", userName).getResultList();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return passenger.get(0);

    }
}
