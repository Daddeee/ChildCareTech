package ChildCareTech.model.canteen;

import ChildCareTech.utils.GenericDAO;

public class CanteenDAO extends GenericDAO<Canteen, Integer> {
    public CanteenDAO() {
        super(Canteen.class);
    }
}