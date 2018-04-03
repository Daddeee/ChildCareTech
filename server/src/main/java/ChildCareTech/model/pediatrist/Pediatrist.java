package ChildCareTech.model.pediatrist;

import ChildCareTech.model.adult.Adult;
import ChildCareTech.model.kid.Kid;
import ChildCareTech.model.person.Person;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("1")
public class Pediatrist extends Adult {

    @OneToMany(mappedBy = "pediatrist")
    private Set<Kid> kids = new HashSet<>();

    public Pediatrist() {
        super();
    }

    public Pediatrist(Person person) {
        super(person);
    }

    public Pediatrist(Person person, Set<Kid> kids) {
        super(person);
        this.kids = kids;
    }

    public Set<Kid> getKids() {
        return kids == null ? Collections.EMPTY_SET : kids;
    }

    private void setKids(Set<Kid> kids) {
        this.kids = kids;
    }


}
