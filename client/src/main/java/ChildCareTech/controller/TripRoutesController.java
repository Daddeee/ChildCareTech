package ChildCareTech.controller;

import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.EventStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.util.Comparator;


public class TripRoutesController {
    @FXML
    protected TableView<RouteDTO> routesTable;
    @FXML
    protected Button startEvent;
    @FXML
    protected Button stopEvent;

    private ObservableList<RouteDTO> data = FXCollections.observableArrayList();
    private RouteDTO currentRoute;
    private boolean isStarting;

    public void initData(TripDTO tripDTO) {
        data.addAll(tripDTO.getRoutes());
        FXCollections.sort(data, Comparator.comparing(RouteDTO::getRouteNumber));
        routesTable.setItems(data);

        for(RouteDTO r : data){
            if(r.getStatus().equals(EventStatus.WAIT)){
                currentRoute = r;
                isStarting = true;
            } else if(r.getStatus().equals(EventStatus.OPEN)){
                currentRoute = r;
                isStarting = false;
            }
        }
    }
}
