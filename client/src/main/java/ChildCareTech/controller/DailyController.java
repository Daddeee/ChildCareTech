package ChildCareTech.controller;

import ChildCareTech.services.SessionService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DailyController {
    @FXML
    protected Label test;

    public void initialize(){
        try {
            test.setText(SessionService.getSession().getCurrentWorkDay().getDate().toString());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
