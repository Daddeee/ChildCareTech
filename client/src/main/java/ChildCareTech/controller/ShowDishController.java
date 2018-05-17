package ChildCareTech.controller;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.services.AccessorWindowService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class ShowDishController implements AccessorWindowController{
    @FXML
    protected TableView<FoodDTO> ingredientsTable;
    @FXML
    protected Label nameLabel;

    public void initData(DishDTO dishDTO){
        nameLabel.setText(dishDTO.getName());

        ingredientsTable.getItems().clear();
        ingredientsTable.getItems().addAll(dishDTO.getFoods());
    }
    public void setAccessorWindowService(AccessorWindowService accessorWindowService) { }
}
