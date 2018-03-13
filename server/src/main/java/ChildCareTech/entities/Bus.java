package ChildCareTech.entities;

import javax.persistence.*;

@Entity
@Table(name="buses")
public class Bus implements iEntity<Bus, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, unique = true)
    private String licensePlate;

    public Bus(){}
    public Bus(String licensePlate) { this.licensePlate = licensePlate; }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    private void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public Integer getPrimaryKey() {
        return getId();
    }

    @Override
    public void setPrimaryKey(Bus o) {
        setId(o.getPrimaryKey());
    }
}
