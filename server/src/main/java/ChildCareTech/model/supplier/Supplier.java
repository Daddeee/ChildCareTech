package ChildCareTech.model.supplier;

import ChildCareTech.model.adult.Adult;
import ChildCareTech.model.person.Person;
import ChildCareTech.model.supply.Supply;

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "supplier")
    private Set<Supply> supplies;

    public Set<Supply> getSupplies() {
        return supplies == null ? Collections.EMPTY_SET : supplies;
    }

    public void setSupplies(Set<Supply> supplies) {
        this.supplies = supplies;
    }

}
