package ChildCareTech;

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
    public Pediatrist(Person person, Set<Kid> kids) { super(person); this.kids = kids; }

    public Set<Kid> getKids() {
        return new HashSet<>(kids);
    }

    private void setKids (Set<Kid> kids) {
        this.kids = kids;
    }
}
