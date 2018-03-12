package ChildCareTech.entities;

import java.io.Serializable;

public interface iEntity<T extends iEntity<T, K> ,K extends Serializable> {
    K getPrimaryKey();
    void setPrimaryKey(T o);
}
