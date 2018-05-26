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

public abstract class AbstractGenericDAO<T extends iEntity, K extends Serializable> {

    private Class<T> persistentClass;
    protected Session session;
    private Validator validator;

    public AbstractGenericDAO(Class<T> clazz) {
        this.persistentClass = clazz;
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
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

    public void create(T obj) throws ValidationFailedException{
        validateEntity(obj);
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

    public List<T> read(String paramName, Object paramValue) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(paramName, paramValue);
        return read(map);
    }

    public List<T> readAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(persistentClass);
        Root<T> root = criteria.from(persistentClass);
        criteria.select(root);
        return session.createQuery(criteria).getResultList();
    }

    public void update(T updatedObj) throws ValidationFailedException {
        validateEntity(updatedObj);
        session.merge(updatedObj);
    }

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

    public abstract void initializeLazyRelations(T obj);
}
