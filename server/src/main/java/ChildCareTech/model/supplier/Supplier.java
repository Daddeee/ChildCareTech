package ChildCareTech.model.supplier;

import ChildCareTech.model.supply.Supply;
import ChildCareTech.model.adult.Adult;
import ChildCareTech.model.person.Person;

import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@DiscriminatorValue("2")
public class Supplier extends Adult {
    public Supplier() { super(); }
    public Supplier(Person person) { super(person); }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "supplier")
    private Set<Supply> supplies;

    public Set<Supply> getSupplies() { return supplies == null ? Collections.EMPTY_SET : supplies; }

    private void setSupplies(Set<Supply> supplies) { this.supplies = supplies; }

}
