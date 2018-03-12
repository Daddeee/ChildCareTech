package ChildCareTech;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "workdays")
public class WorkDay implements iEntity<WorkDay, Date>{
    @Id
    private Date date;


    public WorkDay() { }

    @Override
    public Date getPrimaryKey() {
        return date;
    }

    @Override
    public void setPrimaryKey(WorkDay o) {
        this.date = o.getPrimaryKey();
    }
}
