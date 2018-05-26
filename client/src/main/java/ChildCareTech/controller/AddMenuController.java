package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.common.DTO.MenuDTO;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.services.AccessorWindowService;
import ChildCareTech.services.AlertWindowService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;

public class AddMenuController implements AccessorWindowController {
    @FXML
    protected TableView<DishDTO> availableDishesTable;
    @FXML
    protected TableView<DishDTO> selectedDishesTable;

    private MealDTO currentMealDTO;
    private AccessorWindowService accessorWindowService;
    private ObservableList<DishDTO> selectedDishes = FXCollections.observableArrayList();
    private ObservableList<DishDTO> availableDishes = FXCollections.observableArrayList();
    private AlertWindowService alertWindowService;

    @FXML
    public void initialize() {
        availableDishesTable.setItems(availableDishes);
        selectedDishesTable.setItems(selectedDishes);
        initTable();
        alertWindowService = new AlertWindowService();
    }

    public void initData(MealDTO mealDTO){
        if(mealDTO == null)
            return;
        currentMealDTO = mealDTO;
        if(mealDTO.getMenu() != null) {
            selectedDishes.addAll(mealDTO.getMenu().getDishes());
            availableDishes.removeAll(selectedDishes);
        }
    }

    @FXML
    protected void addDishButtonAction(ActionEvent event) {
        DishDTO dish = availableDishesTable.getSelectionModel().getSelectedItem();

        if (dish != null) {
            selectedDishes.add(dish);
            availableDishes.remove(dish);
        }
    }

    @FXML
    protected void removeDishButtonAction(ActionEvent event){
        DishDTO dish = selectedDishesTable.getSelectionModel().getSelectedItem();

        if (dish != null) {
            availableDishes.add(dish);
            selectedDishes.remove(dish);
        }
    }

    @FXML
    protected void saveButtonAction(ActionEvent event){
        Set<DishDTO> dishes = new HashSet<>(selectedDishesTable.getItems());
        MenuDTO menuDTO = currentMealDTO.getMenu();
        if(menuDTO == null)
            menuDTO = new MenuDTO(0, currentMealDTO, 0, null);
        try{
            for(DishDTO d : menuDTO.getDishes())
                Client.getSessionService().getSession().removeDishFromMenu(menuDTO, d);

            for(DishDTO d : dishes)
                Client.getSessionService().getSession().addDishToMenu(menuDTO, d);

            accessorWindowService.refreshTable();
        } catch (RemoteException e){
            e.printStackTrace();
        }
        accessorWindowService.close();
    }
    private void initTable() {
        availableDishes.clear();
        try{
            availableDishes.addAll(Client.getSessionService().getSession().getAllDishes());
        } catch (RemoteException e){
            e.printStackTrace();
        }
    }

    public void setAccessorWindowService(AccessorWindowService accessorWindowService) {
        this.accessorWindowService = accessorWindowService;
    }
}
