package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Adult;
import ChildCareTech.utils.AbstractGenericDAO;
import org.hibernate.Hibernate;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AdultDAO extends AbstractGenericDAO<Adult, Integer> {
    public AdultDAO() {
        super(Adult.class);
    }


    public List<Adult> readAllExclusive() {
        //DetachedCriteria adultCrit = DetachedCriteria.forClass(Adult.class).add(Restrictions.eq("role", ))

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Adult> criteria = builder.createQuery(Adult.class);
        Root<Adult> root = criteria.from(Adult.class);
        criteria.select(root).where(builder.equal(root.get("role"), "0"));
        return session.createQuery(criteria).getResultList();
    }

    @Override
    public void initializeLazyRelations(Adult obj) {
        initializeContactsRelation(obj);
    }

    public void initializeContactsRelation(Adult obj){
        Hibernate.initialize(obj.getContacts());
    }
}