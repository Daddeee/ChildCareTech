package ChildCareTech;

import java.io.Serializable;

public interface DAOEntity<K extends Serializable> {
    K getPrimaryKey();
}
