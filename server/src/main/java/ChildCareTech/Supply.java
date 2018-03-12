package ChildCareTech;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "supplies")
public class Supply implements iEntity<Supply, Integer>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Food food;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private Date date;

    public Supply(){}
    public Supply(Supplier supplier, Food food, int quantity, Date date){
        this.supplier = supplier;
        this.food = food;
        this.quantity = quantity;
        this.date = date;
    }

    public Supplier getSupplier() { return supplier; }

    private void setSupplier(Supplier supplier) { this.supplier = supplier; }

    public Food getFoods() { return food; }

    private void setFoods(Food food) { this.food = food; }

    public int getQuantity() { return quantity; }

    private void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public Integer getPrimaryKey() { return id; }
    @Override
    public void setPrimaryKey(Supply a) { this.id = a.getPrimaryKey(); }
}

