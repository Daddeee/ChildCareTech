package ChildCareTech.model;

import ChildCareTech.model.iEntity;
import ChildCareTech.utils.exceptions.ValidationFailedException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.Serializable;
import java.util.*;

/**
 * Represent a generic Data Access Object.
 * <p>
 * Provides implementations for the basic CRUD operations.
 *
 * @param <T> the type of entity on which this DAO is operating
 * @param <K> the type of the key of the aforementioned entity
 */
public abstract class AbstractGenericDAO<T extends iEntity, K extends Serializable> {
    private Class<T> persistentClass;

    /**
     * The Hibernate Session needed by this dao to perform every operations.
     * It must be opened and with an active transaction to perform successfully any operation.
     * The manage of sessions/transaction is job of the developer that uses this class.
     */
    protected Session session;

    private Validator validator;

    /**
     * Create a new instance of this object and initialize the correspondent Hibernate validator.
     *
     * @param clazz the managed entity's class
     */
    public AbstractGenericDAO(Class<T> clazz) {
        this.persistentClass = clazz;
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * Trigger the load of every lazy relationship for the given entity.
     *
     * @param obj the provided entity.
     */
    public abstract void initializeLazyRelations(T obj);

    /**
     * @param s the Hibernate {@link #session}.
     */
    public void setSession(Session s) {
        this.session = s;
    }

    /**
     * @return the Hibernate {@link #session}
     */
    protected Session getSession() {
        if (session == null)
            throw new IllegalStateException("Session has not been set on DAO before usage");
        return session;
    }

    /**
     * @return the managed entity's class
     */
    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    /**
     * Save the provided entity in the database.
     *
     * @param obj the entity to be saved in the database.
     * @throws ValidationFailedException if the provided entity does not pass some validations constraints.
     * @throws org.hibernate.HibernateException if some database constraints are violated.
     */
    public void create(T obj) throws ValidationFailedException{
        validateEntity(obj);
        session.save(obj);
    }

    /**
     * Get from the database the entity that has the primary key equals to the provided key.
     *
     * @param key the key to fetch from the database
     * @return the entity with corresponding primary key
     */
    public T read(K key) {
        return session.get(persistentClass, key);
    }

    /**
     * Get from the database the entity that has the primary key equals to the primary key of the provided entity.
     *
     * @param obj the entity provided
     * @return the entity with corresponding primary key
     */
    @SuppressWarnings("unchecked")
    public T read(T obj) {
        return read((K) obj.getPrimaryKey());
    }

    /**
     * Read a list of entities matching the constraints specified in the params map.
     * <p>
     * Each entry of the aforementioned map specify the value for an attribute that an entity must match to be read.
     * For each entry, a where clause is added to the read query: (String) attribute = (Object) value.
     *
     * @param params a map containing the name of the attribute as key and the corresponding value as value.
     * @return a list of entity matching the specified constraints.
     */
    @SuppressWarnings("unchecked")
    public List<T> read(Map<String, Object> params) {
        StringBuilder query = new StringBuilder("from " + persistentClass.getName() + " where ");
        List<Map.Entry<String, Object>> pairList = new ArrayList<>(params.entrySet());

        for (int i = 0; i < pairList.size(); i++) {
            query.append(pairList.get(i).getKey() + " = :" + pairList.get(i).getKey() + " and ");
        }
        query.append("1=1");

        Query queryToFill = session.createQuery(query.toString());
        for (int i = 0; i < pairList.size(); i++) {
            queryToFill.setParameter(pairList.get(i).getKey(), pairList.get(i).getValue());
        }

        return queryToFill.list();
    }

    /**
     * Read a list of entities matching the constraints specified in the params map.
     * <p>
     * Each entry of the aforementioned map specify the value for an attribute that an entity must match to be read.
     * For each entry, a where clause is added to the read query: (String) attribute = (Object) value.
     *
     * @param paramName the name of the attribute.
     * @param paramValue the value of the attribute.
     * @return a list of entity matching the specified constraints.
     */
    public List<T> read(String paramName, Object paramValue) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(paramName, paramValue);
        return read(map);
    }

    /**
     *
     * @return a list containing all the entities of this type saved in the database.
     */
    public List<T> readAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(persistentClass);
        Root<T> root = criteria.from(persistentClass);
        criteria.select(root);
        return session.createQuery(criteria).getResultList();
    }

    /**
     * Update the provided entity's state in the database.
     *
     * @param updatedObj the entity whose state is going to be saved in the database.
     * @throws ValidationFailedException if the provided entity does not pass some validations constraints.
     * @throws org.hibernate.HibernateException if some database constraints are violated.
     */
    public void update(T updatedObj) throws ValidationFailedException {
        validateEntity(updatedObj);
        session.merge(updatedObj);
    }

    /**
     * Remove the provided entity from the database.
     *
     * @param obj the entity to be removed.
     * @throws org.hibernate.HibernateException if some database constraints are violated.
     */
    public void delete(T obj) {
        session.delete(obj);
    }

    private void validateEntity(T entity) throws ValidationFailedException{
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);
        if(constraintViolations.isEmpty()) return;

        StringBuilder errorMessage = new StringBuilder();
        for(ConstraintViolation<T> e : constraintViolations){
            errorMessage.append(e.getMessage());
            errorMessage.append("\n");
        }

        throw new ValidationFailedException(errorMessage.toString());
    }
}
