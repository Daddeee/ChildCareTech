package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.common.DTO.CheckpointDTO;
import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.services.AccessorWindowService;
import ChildCareTech.services.ActiveControllersList;
import ChildCareTech.utils.ReportTableData;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.WindowEvent;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class TripPresenceReportController implements AccessorWindowController {
    @FXML
    protected TableView<ReportTableData> reportTable;

    private AccessorWindowService accessorWindowService;
    private EventDTO currentEvent;

    @FXML
    public void initialize() {
    }

    public void initData(EventDTO eventDTO){
        currentEvent = eventDTO;
        refreshTable();
    }

    public void refreshTable(){
        List<KidDTO> allKids;
        List<CheckpointDTO> allAssociatedCheckpoints;

        reportTable.getItems().clear();
        try{
            allKids = Client.getSessionService().getSession().getAllKids();
            allAssociatedCheckpoints = new ArrayList<>(Client.getSessionService().getSession().getEventCheckpoints(currentEvent));
        } catch(RemoteException e){
            e.printStackTrace();
            return;
        }

        CheckpointDTO tmp;
        for(KidDTO k : allKids) {
            tmp = null;

            for (CheckpointDTO c : allAssociatedCheckpoints){
                if (c.getPerson().getFiscalCode().equals(k.getPerson().getFiscalCode())) {
                    tmp = c;
                    break;
                }
            }

            reportTable.getItems().add(ReportTableData.buildFromDTOs(k, tmp));
        }
    }
    public void setAccessorWindowService(AccessorWindowService accessorWindowService) {
        this.accessorWindowService = accessorWindowService;
        this.accessorWindowService.setOnCloseAction(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                clearInstance();
            }
        });
    }
    public void clearInstance() {
        ActiveControllersList.removeTripPresenceReportController(this);
    }
    public void notifyUpdate() { }
}
