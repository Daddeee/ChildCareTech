package ChildCareTech.model;

import java.io.Serializable;

/**
 * This interface represent a generic Entity.
 *
 * @param <T> the entity type.
 * @param <K> the primary key type.
 */
public interface iEntity<T extends iEntity<T, K>, K extends Serializable> {

    /**
     * @return the entity's primary key.
     */
    K getPrimaryKey();
}
