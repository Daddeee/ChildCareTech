package ChildCareTech.controller;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.DTO.FoodDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class ShowDishController {
    @FXML
    protected TableView<FoodDTO> ingredientsTable;
    @FXML
    protected Label nameLabel;

    public void initData(DishDTO dishDTO){
        nameLabel.setText(dishDTO.getName());

        ingredientsTable.getItems().clear();
        ingredientsTable.getItems().addAll(dishDTO.getFoods());
    }
}
