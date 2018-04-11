package ChildCareTech.model.entities;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.Set;

@Entity
@DiscriminatorValue("2")
public class Supplier extends Adult {
    public Supplier() {
        super();
    }

    public Supplier(Person person) {
        super(person);
    }

    public Supplier(int id, Person person) {
        super(id, person);
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "supplier")
    private Set<Supply> supplies;

    public Set<Supply> getSupplies() {
        return supplies == null ? Collections.EMPTY_SET : supplies;
    }

    public void setSupplies(Set<Supply> supplies) {
        this.supplies = supplies;
    }

}
