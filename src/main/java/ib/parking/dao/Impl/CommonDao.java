package ib.parking.dao.Impl;

import java.io.Serializable;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class CommonDao<T> implements GenericDao<T> {

    private @Autowired SessionFactory sf;

    @Override
    public Serializable save(T entity) {
        return session().save(entity);
    }

    protected Session session() {
        Session session = sf.getCurrentSession();
        return session;
    }

    protected Query query(String query) {
        return session().createQuery(query);
    }

}
