package ChildCareTech.controller;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.common.EventStatus;
import ChildCareTech.services.AccessorSceneManager;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.SessionService;
import ChildCareTech.utils.RestrictedDatePicker;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Set;

public class WorkDayController {
    @FXML
    protected TableView<EventDTO> eventsTable;
    @FXML
    protected RestrictedDatePicker workDayDatePicker;
    @FXML
    protected Label alertLabel;

    public void initialize() {
        eventsTable.setRowFactory(eventDTOTableView -> {
            final TableRow<EventDTO> row = new TableRow<>();

            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem codeInput = new MenuItem("Acquisisci codici");
            codeInput.setOnAction(event ->  {
                contextMenu.hide();
                if(row.getItem().getEventStatus().equals(EventStatus.CLOSED))
                    alertLabel.setText("This event is now closed.");
                else if(row.getItem().getEventStatus().equals(EventStatus.WAIT))
                    alertLabel.setText("This event has not yet been opened.");
                else {
                    alertLabel.setText("");
                    try {
                        AccessorSceneManager.loadCodeInput(row.getItem());
                    } catch (IOException ex) {
                        System.err.println("Can't load showTrip window");
                        ex.printStackTrace();
                    }
                }
            });
            contextMenu.getItems().add(codeInput);

            final MenuItem eventReport = new MenuItem("Consulta report");
            eventReport.setOnAction(event -> {
                contextMenu.hide();
                alertLabel.setText("");
                try {
                    AccessorSceneManager.loadEventReport(row.getItem());
                } catch (IOException ex) {
                    System.err.println("Can't load eventReport window");
                    ex.printStackTrace();
                }
            });
            contextMenu.getItems().add(eventReport);

            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu)
            );

            return row;

        });
    }

    @FXML
    public void updateTable(ActionEvent event){
        WorkDayDTO w;
        try{
            w = SessionService.getSession().getWorkDay(workDayDatePicker.getValue());
            refreshTable(w.getEvents());
        } catch(RemoteException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void backButtonAction(ActionEvent event) {
        try {
            MainSceneManager.loadHome();
        } catch (IOException ex) {
            System.err.println("Can't load addKid window");
            ex.printStackTrace();
        }
    }

    private void refreshTable(Set<EventDTO> events){
        eventsTable.getItems().clear();
        eventsTable.getItems().addAll(events);
    }
}
