package edu.ykuchirka.Dao;

import java.util.List;

/**
 * Created by Yura on 29.03.2017.
 */
public interface AbstractDao<T> {
    void insert(T entity);

    void update(T entity);

    void remove(int entityId);

    void remove(T entity);

    T find(int id);

    List<T> findAll();

    int count();
}
