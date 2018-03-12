package ChildCareTech.entities;

import java.util.Set;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@DiscriminatorValue("2")
public class Supplier extends Adult {
    public Supplier() { super(); }
    public Supplier(Person person) { super(person); }

    @OneToMany(mappedBy = "supplier")
    private Set<Supply> supplies;

    public Set<Supply> getSupplies() { return new HashSet<>(supplies); }

    private void setSupplies(Set<Supply> supplies) { this.supplies = supplies; }

}
