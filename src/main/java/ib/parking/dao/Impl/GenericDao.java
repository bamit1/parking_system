package ib.parking.dao.Impl;

import java.io.Serializable;

public interface GenericDao<T> {

    Serializable save(T entity);

}
