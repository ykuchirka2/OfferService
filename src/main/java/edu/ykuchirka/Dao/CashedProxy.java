package edu.ykuchirka.Dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yura on 29.03.2017.
 */
public abstract class CashedProxy<T> implements AbstractDao<T> {

    public static final String CASED_LIST_IS_EMPTY = "Cashed list is empty.";

    protected final List<T> list;
    protected Class<T> entityClass;

    public CashedProxy(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.list = new LinkedList<T>();
    }

    public void insert(T entity) {
        if (list != null) {
            list.add(entity);
        } else {
            throw new RuntimeException(CASED_LIST_IS_EMPTY);
        }
    }

    public void update(T entity) {

    }

    @Override
    public void remove(int entityId) {

    }

    @Override
    public void remove(T entity) {

    }

    @Override
    abstract public T find(int id);

    @Override
    public List<T> findAll() {

        List<T> result = null;

        if (list != null) {
            result = new ArrayList<T>(list);
        } else {
            throw new RuntimeException(CASED_LIST_IS_EMPTY);
        }

        return result;
    }

    @Override
    public int count() {
        return 0;
    }
}
