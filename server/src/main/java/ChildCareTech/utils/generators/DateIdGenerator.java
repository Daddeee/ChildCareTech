package ChildCareTech.utils.generators;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Calendar;

public class DateIdGenerator implements IdentifierGenerator{

    private static Calendar cal = Calendar.getInstance();

    public DateIdGenerator() { }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return null;//cal.add(Calendar.DATE, 1).getTime();
    }
}
