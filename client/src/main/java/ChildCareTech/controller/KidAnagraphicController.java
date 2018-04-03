package ChildCareTech.controller;

import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.services.AccessorSceneManager;
import ChildCareTech.services.MainSceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class KidAnagraphicController {

    @FXML
    private Button addButton;
    @FXML
    private Button backButton;
    @FXML
    private Button logOutButton;
    @FXML
    private TableView<PersonDTO> personTable;
    @FXML
    private TableColumn<PersonDTO, String> firstNameColumn;
    @FXML
    private TableColumn<PersonDTO, String> lastNameColumn;
    @FXML
    private TableColumn<PersonDTO, String> fiscalCodeNameColumn;
    @FXML
    private TableColumn<PersonDTO, LocalDate> bDateColumn;
    @FXML
    private TableColumn<PersonDTO, String> addressColumn;


    private List<PersonDTO> items = new ArrayList<>();


    public KidAnagraphicController() {

    }

    @FXML
    public void initialize() {
        //anagList.setItems(items);
    }

    @FXML
    public void addButtonAction(ActionEvent event) {
        try {
            AccessorSceneManager.loadAddPerson();
        } catch (IOException ex) {
            System.err.println("Can't load addKid window");
            ex.printStackTrace();
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
}
