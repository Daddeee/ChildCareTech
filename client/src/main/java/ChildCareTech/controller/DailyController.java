package ChildCareTech.controller;

import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.SessionService;
import ChildCareTech.utils.DailyEventData;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalTime;

public class DailyController {
    @FXML
    protected Label clockLabel;
    @FXML
    protected Label dateLabel;
    @FXML
    protected TableView<DailyEventData> dailyEventDataTable;

    private WorkDayDTO currentWorkDay;

    public void initialize(){
        try{
            currentWorkDay = SessionService.getSession().getCurrentWorkDay();
            dateLabel.setText(currentWorkDay.getDate().toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        refreshTable();
    }

    @FXML
    protected void backButtonAction(ActionEvent e){
        try{
            MainSceneManager.loadHome();
        } catch(IOException ex){
            ex.printStackTrace();
        }
    }

    private void refreshTable() {
        //if(currentWorkDay.isHoliday()) return;

        dailyEventDataTable.getItems().add(
                new DailyEventData(
                    "Ingresso",
                        currentWorkDay.getEntryTime().toString(),
                        currentWorkDay.getEntryTime().plusMinutes(10).toString(),
                        DailyEventData.findStatus(currentWorkDay.getEntryTime(), currentWorkDay.getEntryTime().plusMinutes(10))
                )
        );

        dailyEventDataTable.getItems().add(
                new DailyEventData(
                        "Uscita",
                        currentWorkDay.getExitTime().toString(),
                        currentWorkDay.getExitTime().plusMinutes(10).toString(),
                        DailyEventData.findStatus(currentWorkDay.getExitTime(), currentWorkDay.getExitTime().plusMinutes(10))
                )
        );
    }
}
