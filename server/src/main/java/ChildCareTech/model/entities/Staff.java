package ChildCareTech.model.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("3")
public class Staff extends Adult {
    public Staff() {
        super();
    }

    public Staff(Person person) {
        super(person);
    }

    public Staff(int id, Person person) {
        super(id, person);
    }
}
