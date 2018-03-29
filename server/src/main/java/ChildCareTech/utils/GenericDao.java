package ChildCareTech.utils;

import ChildCareTech.model.iEntity;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public class GenericDao<T extends iEntity, K extends Serializable> {

    private Class<T> persistentClass;
    private Session session;

    public GenericDao(Class<T> clazz) {
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

    public void create(T obj){ session.save(obj); }

    public T read(K key){ return session.get(persistentClass, key); }

    @SuppressWarnings("unchecked")
    public T read(T obj){ return read((K) obj.getPrimaryKey()); }

    @SuppressWarnings("unchecked")
    public void update(T baseObj, T updatedObj){
        updatedObj.setPrimaryKey(baseObj);
        session.merge(updatedObj);
    }

    public void delete(T obj){ session.delete(obj); }

    @SuppressWarnings("unchecked")
    public List<T> readAll() { return session.createQuery("from " + persistentClass.getName()).list(); }
}
