package ChildCareTech.controller;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.common.DTO.MenuDTO;
import ChildCareTech.services.AccessorWindowService;
import ChildCareTech.services.SessionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    @FXML
    public void initialize() {
        availableDishesTable.setItems(availableDishes);
        selectedDishesTable.setItems(selectedDishes);
        initTable();
    }

    public void initData(MealDTO mealDTO){
        if(mealDTO == null)
            return;
        currentMealDTO = mealDTO;
        if(mealDTO.getMenu() != null) {
            selectedDishes.addAll(mealDTO.getMenu().getDishes());
            for(DishDTO dishDTO : mealDTO.getMenu().getDishes())
                availableDishes.remove(dishDTO);
        }
    }

    @FXML
    protected void addDishButtonAction(ActionEvent event){
        int rowIndex = availableDishesTable.getSelectionModel().getFocusedIndex();

        selectedDishesTable.getItems().add(availableDishesTable.getItems().get(rowIndex));
        availableDishesTable.getItems().remove(rowIndex);
    }

    @FXML
    protected void removeDishButtonAction(ActionEvent event){
        int rowIndex = selectedDishesTable.getSelectionModel().getFocusedIndex();

        availableDishesTable.getItems().add(selectedDishesTable.getItems().get(rowIndex));
        selectedDishesTable.getItems().remove(rowIndex);
    }

    @FXML
    protected void saveButtonAction(ActionEvent event){
        Set<DishDTO> dishes = new HashSet<>(selectedDishesTable.getItems());
        MenuDTO menuDTO = new MenuDTO(0, currentMealDTO, 0, dishes);
        currentMealDTO.setMenu(menuDTO);
        menuDTO.setDishes(dishes);

        try{
            SessionService.getSession().createMenu(currentMealDTO);
            accessorWindowService.close();
            accessorWindowService.refreshTable();
        } catch (RemoteException e){
            e.printStackTrace();
        }
    }
    private void initTable() {
        availableDishes.clear();
        try{
            availableDishes.addAll(SessionService.getSession().getAllDishes());
        } catch (RemoteException e){
            e.printStackTrace();
        }
    }

    public void setAccessorWindowService(AccessorWindowService accessorWindowService) {
        this.accessorWindowService = accessorWindowService;
    }
}
