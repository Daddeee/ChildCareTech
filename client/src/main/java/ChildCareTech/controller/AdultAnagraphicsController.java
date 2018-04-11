package ChildCareTech.controller;

import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.services.AccessorSceneManager;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.ObservableDTOs.ObservableAdult;
import ChildCareTech.services.SessionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdultAnagraphicsController {

    @FXML
    private Button addButton;
    @FXML
    private Button backButton;
    @FXML
    private Button logOutButton;
    @FXML
    private TableView<ObservableAdult> personTable;
    @FXML
    private TableColumn<ObservableAdult, String> firstNameColumn;
    @FXML
    private TableColumn<ObservableAdult, String> lastNameColumn;
    @FXML
    private TableColumn<ObservableAdult, String> fiscalCodeColumn;
    @FXML
    private TableColumn<ObservableAdult, LocalDate> bDateColumn;
    @FXML
    private TableColumn<ObservableAdult, String> addressColumn;

    private ObservableList<ObservableAdult> items = FXCollections.observableArrayList();

    public AdultAnagraphicsController() { }

    @FXML
    public void initialize() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<ObservableAdult, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<ObservableAdult, String>("lastName"));
        fiscalCodeColumn.setCellValueFactory(new PropertyValueFactory<ObservableAdult, String>("fiscalCode"));
        bDateColumn.setCellValueFactory(new PropertyValueFactory<ObservableAdult, LocalDate>("birthDate"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<ObservableAdult, String>("address"));

        refreshTable();
        personTable.setItems(items);
    }

    @FXML
    public void addButtonAction(javafx.event.ActionEvent event) {
        try {
            AccessorSceneManager.loadSelectAdultKind();
        } catch (IOException ex) {
            System.err.println("Can't load selectAdultKind window");
            ex.printStackTrace();
        }
    }

    @FXML
    public void backButtonAction(javafx.event.ActionEvent event) {
        try {
            MainSceneManager.loadHome();
        } catch (IOException ex) {
            System.err.println("Can't load addKid window");
            ex.printStackTrace();
        }
    }

    public void refreshTable() {
        List<AdultDTO> adultDTOList = new ArrayList<>();
        try {
            adultDTOList = SessionService.getSession().getAllAdults();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        items.clear();
        for (AdultDTO adult : adultDTOList) {
            items.add(new ObservableAdult(adult));
        }
    }

    public void saveNormalAdult(AdultDTO adult) {
        try {
            SessionService.getSession().saveAdult(adult);
        } catch(RemoteException ex) {
            ex.printStackTrace();
        } catch(AddFailedException ex) {
            ex.printStackTrace();
        }
    }
}
