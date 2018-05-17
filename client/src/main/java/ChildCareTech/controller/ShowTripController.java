package ChildCareTech.controller;

import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.services.AccessorWindowService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class ShowTripController implements AccessorWindowController {
    @FXML
    private Label metaField;
    @FXML
    private Label noteField;
    @FXML
    private Label depDateField;
    @FXML
    private Label arrDateField;
    @FXML
    private TableView<RouteDTO> routesTable;
    //private AccessorWindowService accessorWindowService;


    public void initData(TripDTO rowData) {
        metaField.setText(rowData.getMeta());
        noteField.setText(rowData.getNote());
        depDateField.setText(rowData.getDepDate().toString());
        arrDateField.setText(rowData.getArrDate().toString());

        for(RouteDTO r : rowData.getRoutes())
            routesTable.getItems().add(r);
    }
    public void setAccessorWindowService(AccessorWindowService accessorWindowService) { }
}
