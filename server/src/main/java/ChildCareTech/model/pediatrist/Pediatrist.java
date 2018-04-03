package ChildCareTech.model.pediatrist;

import ChildCareTech.model.person.Person;
import ChildCareTech.model.adult.Adult;
import ChildCareTech.model.kid.Kid;

import javax.persistence.*;
import javax.persistence.Entity;

import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("1")
public class Pediatrist extends Adult {

    @OneToMany(mappedBy = "pediatrist")
    private Set<Kid> kids = new HashSet<>();

    public Pediatrist() { super(); }
    public Pediatrist(Person person) { super(person); }
    public Pediatrist(Person person, Set<Kid> kids) { super(person); this.kids = kids; }

    public Set<Kid> getKids() {
        return kids;
    }

    private void setKids (Set<Kid> kids) {
        this.kids = kids;
    }

    
}
