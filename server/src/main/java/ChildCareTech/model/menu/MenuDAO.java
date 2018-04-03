package ChildCareTech.model.menu;

import ChildCareTech.utils.GenericDAO;

public class MenuDAO extends GenericDAO<Menu, Integer> {
    public MenuDAO() {
        super(Menu.class);
    }
}