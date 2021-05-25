package hiber.dao;


import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public List<User> listUsersCars(String carModel, int carSeries) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(
                "from User where userCar.model = :model and userCar.series = :series");
        query.setParameter("model", carModel);
        query.setParameter("series", carSeries);
        return query.getResultList();
    }
}
