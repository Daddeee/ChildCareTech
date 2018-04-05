package ChildCareTech.model.menu;

import ChildCareTech.utils.AbstractGenericDAO;

public class MenuDAO extends AbstractGenericDAO<Menu, Integer> {
    public MenuDAO() {
        super(Menu.class);
    }
}