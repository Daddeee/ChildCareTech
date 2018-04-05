package ChildCareTech.model.canteen;

import ChildCareTech.utils.AbstractGenericDAO;

public class CanteenDAO extends AbstractGenericDAO<Canteen, Integer> {
    public CanteenDAO() {
        super(Canteen.class);
    }
}