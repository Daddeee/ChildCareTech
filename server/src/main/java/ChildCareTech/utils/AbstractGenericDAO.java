package ChildCareTech.utils;

import ChildCareTech.model.iEntity;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractGenericDAO<T extends iEntity, K extends Serializable> {

    private Class<T> persistentClass;
    private Session session;

    public AbstractGenericDAO(Class<T> clazz) {
        this.persistentClass = clazz;
    }

    public void setSession(Session s) {
        this.session = s;
    }

    protected Session getSession() {
        if (session == null)
            throw new IllegalStateException("Session has not been set on DAO before usage");
        return session;
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    public void create(T obj) {
        session.save(obj);
    }

    public T read(K key) {
        return session.get(persistentClass, key);
    }

    @SuppressWarnings("unchecked")
    public T read(T obj) {
        return read((K) obj.getPrimaryKey());
    }

    @SuppressWarnings("unchecked")
    public List<T> read(Map<String, String> params) {
        StringBuilder query = new StringBuilder("from " + persistentClass.getName() + " where ");

        for (Map.Entry<String, String> entry : params.entrySet()) {
            query.append(entry.getKey() + " = '" + entry.getValue() + "' and ");
        }

        query.append("1=1");
        return session.createQuery(query.toString()).list();
    }

    public List<T> read(String paramName, String paramValue) {
        HashMap<String, String> map = new HashMap<>();
        map.put(paramName, paramValue);
        return read(map);
    }

    public List<T> readAll() {
        return read(new HashMap<>());
    }

    @SuppressWarnings("unchecked")
    public void update(T baseObj, T updatedObj) {
        updatedObj.setPrimaryKey(baseObj);
        session.merge(updatedObj);
    }

    public void delete(T obj) {
        session.delete(obj);
    }

    public abstract void initializeLazyRelations(T obj);
}
