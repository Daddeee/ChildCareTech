package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Checkpoint;
import ChildCareTech.model.AbstractGenericDAO;

public class CheckpointDAO extends AbstractGenericDAO<Checkpoint, Integer> {
    public CheckpointDAO() {
        super(Checkpoint.class);
    }

    @Override
    public void initializeLazyRelations(Checkpoint obj) {

    }
}