package ChildCareTech.controller;

import ChildCareTech.common.PersonDTO;
import ChildCareTech.network.DTO.PersonDTOImpl;
import ChildCareTech.services.AccessorSceneManager;
import ChildCareTech.services.AccessorStageService;
import ChildCareTech.services.SessionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class AnagraphicController {

    //@FXML
    //private ListView anagList;

    @FXML
    private Button saveButton;

    @FXML
    private Button addButton;

    private List<PersonDTO> items = new ArrayList<>();


    public AnagraphicController() {

    }

    @FXML
    public void initialize() {
        //anagList.setItems(items);
    }

    @FXML
    public void addButtonAction(ActionEvent event) {
        try {
            items = SessionService.getSession().getAllPeople();
        } catch(RemoteException ex) {
            System.err.println("Remote error fetching person list");
            ex.printStackTrace();
        }
        Iterator<PersonDTO> it = items.iterator();

        System.out.println("start person list");
        while(it.hasNext())
            System.out.println(it.next().getFiscalCode());
        System.out.println("end person list");
    }

    @FXML
    public void saveButtonAction(ActionEvent event) {

    }
}
