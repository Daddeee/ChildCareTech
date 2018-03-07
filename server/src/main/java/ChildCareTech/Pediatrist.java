package ChildCareTech;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("1")
public class Pediatrist extends Adult {

    @OneToMany(mappedBy = "pediatrist")
    private Set<Kid> kids;

    public Pediatrist() { super(); }
    public Pediatrist(Person person) { super(person); }

    public Set<Kid> getKids() {
        return new HashSet<>(kids);
    }

    private void setKids (Set<Kid> kids) {
        this.kids = kids;
    }
}
