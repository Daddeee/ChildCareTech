package ChildCareTech;

import java.io.Serializable;

public interface iEntity<K extends Serializable> {
    K getPrimaryKey();
}
