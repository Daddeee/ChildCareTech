package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.services.AccessorWindowService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

import java.time.LocalTime;

public class CodeInputWindowController implements AccessorWindowController {
    @FXML
    private VBox acquisitionBox;

    private TextField codeField;
    private Button saveButton;
    private Label alertLabel;

    private EventDTO event;

    public void initialize(){
        setTextInput();
    }

    @FXML
    public void initData(EventDTO event){
        this.event = event;
    }

    private void setTextInput(){
        codeField = new TextField();
        codeField.setPromptText("Insert fiscal code");
        codeField.setPrefHeight(50);
        codeField.setPrefWidth(400);


        saveButton = new Button("Conferma");
        saveButton.setPrefHeight(25);
        saveButton.setPrefWidth(100);
        saveButton.setOnAction(this::saveButtonAction);

        alertLabel = new Label();
        alertLabel.setPrefHeight(25);
        alertLabel.setTextFill(Paint.valueOf("#ff4444"));

        acquisitionBox.getChildren().add(codeField);
        acquisitionBox.getChildren().add(saveButton);
        acquisitionBox.getChildren().add(alertLabel);
    }

    @FXML
    protected void saveButtonAction(ActionEvent ev){
        String fiscalCode = codeField.getText();
        codeField.setText("");

        try{
            Client.getSessionService().getSession().saveCheckpoint(fiscalCode, event, LocalTime.now());
        } catch (Exception e){
            alertLabel.setText(e.getMessage());
        }
    }

    public void setAccessorWindowService(AccessorWindowService accessorWindowService) { }
}
