package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Checkpoint;
import ChildCareTech.model.AbstractGenericDAO;

/**
 * A Data Access Object that operates with Checkpoint entities.
 */
public class CheckpointDAO extends AbstractGenericDAO<Checkpoint, Integer> {
    public CheckpointDAO() {
        super(Checkpoint.class);
    }

    @Override
    public void initializeLazyRelations(Checkpoint obj) {

    }
}