package ChildCareTech.controller;

import ChildCareTech.common.DTO.CheckpointDTO;
import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.services.AccessorWindowService;
import ChildCareTech.services.SessionService;
import ChildCareTech.utils.ReportTableData;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EventReportController implements AccessorWindowController{
    @FXML
    protected TableView<ReportTableData> reportTable;

    @FXML
    public void initialize() {
    }

    public void initData(EventDTO eventDTO){
        List<KidDTO> allKids;
        List<CheckpointDTO> allAssociatedCheckpoints;

        try{
            allKids = SessionService.getSession().getAllKids();
            allAssociatedCheckpoints = new ArrayList<>(SessionService.getSession().getEventCheckpoints(eventDTO));
        } catch(RemoteException e){
            e.printStackTrace();
            return;
        }

        List<ReportTableData> retrievedData = new ArrayList<>();
        CheckpointDTO tmp;
        for(KidDTO k : allKids) {
            tmp = null;

            for (CheckpointDTO c : allAssociatedCheckpoints){
                if (c.getPerson().getFiscalCode().equals(k.getPerson().getFiscalCode())) {
                    tmp = c;
                    break;
                }
            }

            retrievedData.add(ReportTableData.buildFromDTOs(k, tmp));
        }

        refreshTable(retrievedData);
    }

    private void refreshTable(Collection<ReportTableData> elems){
        reportTable.getItems().clear();
        reportTable.getItems().addAll(elems);
    }
    public void setAccessorWindowService(AccessorWindowService accessorWindowService) { }
}
