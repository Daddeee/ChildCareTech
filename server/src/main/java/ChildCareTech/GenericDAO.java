package ChildCareTech;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.List;

public class GenericDAO<T extends DAOEntity> {

    private Class<T> persistentClass;
    private Session session;

    public GenericDAO(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    @SuppressWarnings("unchecked")
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

    public T create(T entity){
        getSession().save(entity);
        return entity;
    }

    @SuppressWarnings("unchecked")
    public T read(T o){
        return (T) getSession().get(getPersistentClass(), o.getPrimaryKey());
    }

    public T read(Serializable id){
        return (T) getSession().get(getPersistentClass(), id);
    }

    /*
    *  We're dealing with immutable objects, so we can't use update() method provided by Hibernate.
    *
    *  DAOEntity o = new DAOEntity(Serializable sameKey, ...);
    *  session.save(o);
    *
    *  DAOEntity oUpdated = new DAOEntity(Serializable sameKey, ...);
    *  session.update(oUpdated);
    *
    *  --> EXCEPTION: persistence instance with same id already present in session
    *
    *  We need to use merge(): it automatically update persistent instances.
    */
    public T update(T o){
        getSession().merge(o);
        return o;
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return findByCriteria();
    }

    @SuppressWarnings("unchecked")
    public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        Example example =  Example.create(exampleInstance);
        for (String exclude : excludeProperty) {
            example.excludeProperty(exclude);
        }
        crit.add(example);
        return crit.list();
    }

    public void flush() {
        getSession().flush();
    }

    public void clear() {
        getSession().clear();
    }

    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(Criterion... criterion) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        Criteria crit = getSession().createCriteria(getPersistentClass());
        for (Criterion c : criterion) {
            crit.add(c);
        }
        return crit.list();
    }

}
