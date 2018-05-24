package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.DTO.PediatristDTO;
import ChildCareTech.common.DTO.StaffDTO;
import ChildCareTech.common.DTO.SupplierDTO;
import ChildCareTech.common.UserSessionFacade;
import ChildCareTech.services.AccessorWindowService;
import ChildCareTech.services.ObservableDTOs.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class NewAdultAnagraphicsController implements TableWindowControllerInterface {
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private Button detailsButton;
    @FXML
    private TableView<ObservablePersonInterface> personTable;
    @FXML
    private TableColumn<ObservablePersonInterface, String> firstNameColumn;
    @FXML
    private TableColumn<ObservablePersonInterface, String> lastNameColumn;
    @FXML
    private TableColumn<ObservablePersonInterface, String> fiscalCodeColumn;
    @FXML
    private TableColumn<ObservablePersonInterface, LocalDate> bDateColumn;
    @FXML
    private TableColumn<ObservablePersonInterface, String> addressColumn;
    @FXML
    private TableColumn<ObservablePersonInterface, String> roleColumn;

    private ObservableList<ObservablePersonInterface> items = FXCollections.observableArrayList();
    private ObservableList<ObservableSupplier> suppliers = FXCollections.observableArrayList();
    private ObservableList<ObservableStaff> staffMembers = FXCollections.observableArrayList();
    private ObservableList<ObservablePediatrist> pediatrists = FXCollections.observableArrayList();
    private ObservableList<ObservableAdult> adults = FXCollections.observableArrayList();
    private AccessorWindowService accessorWindowService = new AccessorWindowService(this);

    @FXML
    public void initialize() {
        initTable();
        initMenu();
    }

    @FXML
    public void addButtonAction(javafx.event.ActionEvent event) {
        try {
            accessorWindowService.loadAddAdultWindow();
        } catch (IOException ex) {
            System.err.println("Can't load selectAdultKind window");
            ex.printStackTrace();
        }
    }
    @FXML
    public void detailsButtonAction(ActionEvent event) {
        ObservablePersonInterface selectedItem = personTable.getSelectionModel().getSelectedItem();
        try {
            if(selectedItem instanceof ObservableAdult) {
                accessorWindowService.loadShowAdultWindow((ObservableAdult) selectedItem);
            }
            else if(selectedItem instanceof ObservablePediatrist) {
                accessorWindowService.loadShowPediatristWindow((ObservablePediatrist) selectedItem);
            }
            else if(selectedItem instanceof ObservableStaff) {
                accessorWindowService.loadShowStaffMember((ObservableStaff) selectedItem);
            }
            else if(selectedItem instanceof ObservableSupplier) {
                accessorWindowService.loadShowSupplierWindow((ObservableSupplier) selectedItem);
            }
        } catch (IOException ex) {
            System.err.println("Can't load show window");
            ex.printStackTrace();
        }
    }
    @FXML
    public void deleteButtonAction(ActionEvent event) {
        ObservablePersonInterface selectedItem = personTable.getSelectionModel().getSelectedItem();
        try {
            if(selectedItem instanceof ObservableAdult) {
                Client.getSessionService().getSession().removeAdult(((ObservableAdult) selectedItem).getDTO());
            }
            else if(selectedItem instanceof ObservablePediatrist) {
                Client.getSessionService().getSession().removePediatrist(((ObservablePediatrist) selectedItem).getDTO());
            }
            else if(selectedItem instanceof ObservableStaff) {
                Client.getSessionService().getSession().removeStaff(((ObservableStaff) selectedItem).getDTO());
            }
            else if(selectedItem instanceof ObservableSupplier) {
                Client.getSessionService().getSession().removeSupplier(((ObservableSupplier) selectedItem).getDTO());
            }
        } catch (RemoteException ex) {
            System.err.println("error remote");
            ex.printStackTrace();
        }
        refreshTable();
    }
    @FXML
    public void editButtonAction(ActionEvent event) {
        try {
            accessorWindowService.loadEditAdultWindow(personTable.getSelectionModel().getSelectedItem());
        } catch(IOException ex) {
            System.err.println("Can't load editAdultKind window");
            ex.printStackTrace();
        }
    }

    public void refreshTable() {
        UserSessionFacade session = Client.getSessionService().getSession();
        List<AdultDTO> adultDTOList = new ArrayList<>();
        List<PediatristDTO> pediatristDTOList = new ArrayList<>();
        List<StaffDTO> staffDTOList = new ArrayList<>();
        List<SupplierDTO> supplierDTOList = new ArrayList<>();
        try {
            adultDTOList = session.getAllAdultsExclusive();
            pediatristDTOList = session.getAllPediatrists();
            staffDTOList = session.getAllStaff();
            supplierDTOList = session.getAllSuppliers();

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        items.clear();
        for (AdultDTO adult : adultDTOList) {
            items.add(new ObservableAdult(adult));
        }
        for(PediatristDTO pediatrist : pediatristDTOList) {
            items.add(new ObservablePediatrist(pediatrist));
        }
        for(StaffDTO staff : staffDTOList) {
            items.add(new ObservableStaff(staff));
        }
        for(SupplierDTO supplier : supplierDTOList) {
            items.add(new ObservableSupplier(supplier));
        }
    }

    private void initTable() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<ObservablePersonInterface, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<ObservablePersonInterface, String>("lastName"));
        fiscalCodeColumn.setCellValueFactory(new PropertyValueFactory<ObservablePersonInterface, String>("fiscalCode"));
        bDateColumn.setCellValueFactory(new PropertyValueFactory<ObservablePersonInterface, LocalDate>("birthDate"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<ObservablePersonInterface, String>("address"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<ObservablePersonInterface, String>("role"));
        refreshTable();
        personTable.setItems(items);
    }

    private void initMenu() {
        editButton.setDisable(true);
        detailsButton.setDisable(true);
        deleteButton.setDisable(true);

        personTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                editButton.setDisable(false);
                detailsButton.setDisable(false);
                deleteButton.setDisable(false);
            }
            else {
                editButton.setDisable(true);
                detailsButton.setDisable(true);
                deleteButton.setDisable(true);
            }
        });
    }

    public void clearChildInstances() { accessorWindowService.close(); }
    public void notifyUpdate() {

    }
}
