package ChildCareTech;

import java.io.Serializable;

public interface Entity<K extends Serializable> {
    K getPrimaryKey();
}
