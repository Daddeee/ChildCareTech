package ChildCareTech.controller;

import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.DTO.PediatristDTO;
import ChildCareTech.common.DTO.StaffDTO;
import ChildCareTech.common.DTO.SupplierDTO;
import ChildCareTech.common.UserSession;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.services.AccessorSceneManager;
import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.ObservableDTOs.*;
import ChildCareTech.services.SessionService;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

    public AdultAnagraphicsController() { }

    @FXML
    public void initialize() {
        initMenu();

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<ObservablePersonInterface, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<ObservablePersonInterface, String>("lastName"));
        fiscalCodeColumn.setCellValueFactory(new PropertyValueFactory<ObservablePersonInterface, String>("fiscalCode"));
        bDateColumn.setCellValueFactory(new PropertyValueFactory<ObservablePersonInterface, LocalDate>("birthDate"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<ObservablePersonInterface, String>("address"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<ObservablePersonInterface, String>("role"));

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
        UserSession session = SessionService.getSession();
        List<AdultDTO> adultDTOList = new ArrayList<>();
        List<PediatristDTO> pediatristDTOList = new ArrayList<>();
        List<StaffDTO> staffDTOList = new ArrayList<>();
        List<SupplierDTO> supplierDTOList = new ArrayList<>();
        try {
            adultDTOList = session.getAllAdultsEx();
            pediatristDTOList = session.getAllPediatrists();
            staffDTOList = session.getAllStaffMembers();
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

    public void saveNormalAdult(AdultDTO adult) {
        try {
            SessionService.getSession().saveAdult(adult);
        } catch(RemoteException ex) {
            ex.printStackTrace();
        } catch(AddFailedException ex) {
            ex.printStackTrace();
        }
    }


    private void initMenu() {
        personTable.setRowFactory(tripDTOTableView -> {
            final TableRow<ObservablePersonInterface> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem showGenericAdult = new MenuItem("Dettagli");
            showGenericAdult.setOnAction(event ->  {
                contextMenu.hide();

                try {
                    if(row.getItem() instanceof ObservableAdult) {
                        AccessorSceneManager.loadShowAdult((ObservableAdult) row.getItem());
                    }
                    else if(row.getItem() instanceof ObservablePediatrist) {
                        AccessorSceneManager.loadShowPediatrist((ObservablePediatrist) row.getItem());
                    }
                    else if(row.getItem() instanceof ObservableStaff) {
                        AccessorSceneManager.loadShowStaffMember((ObservableStaff) row.getItem());
                    }
                    else if(row.getItem() instanceof ObservableSupplier) {
                        AccessorSceneManager.loadShowSupplier((ObservableSupplier) row.getItem());
                    }
                } catch (IOException ex) {
                    System.err.println("Can't load show window");
                    ex.printStackTrace();
                }
            });
            contextMenu.getItems().add(showGenericAdult);

            final MenuItem deleteGenericAdult= new MenuItem("Elimina");
            deleteGenericAdult.setOnAction(event -> {
                contextMenu.hide();

                try {
                    if(row.getItem() instanceof ObservableAdult) {
                        SessionService.getSession().removeAdult(((ObservableAdult) row.getItem()).getDTO());
                    }
                    else if(row.getItem() instanceof ObservablePediatrist) {
                        SessionService.getSession().removePediatrist(((ObservablePediatrist) row.getItem()).getDTO());
                    }
                    else if(row.getItem() instanceof ObservableStaff) {
                        SessionService.getSession().removeStaffMember(((ObservableStaff) row.getItem()).getDTO());
                    }
                    else if(row.getItem() instanceof ObservableSupplier) {
                        SessionService.getSession().removeSupplier(((ObservableSupplier) row.getItem()).getDTO());
                    }
                } catch (RemoteException ex) {
                    System.err.println("error remote");
                    ex.printStackTrace();
                }
                refreshTable();
            });
            contextMenu.getItems().add(deleteGenericAdult);

            final MenuItem allergiesManagement = new MenuItem("Allergie");
            allergiesManagement.setOnAction(event -> {
                contextMenu.hide();

                try {
                    if(row.getItem() instanceof ObservableAdult) {
                        AccessorSceneManager.loadAllergies(((ObservableAdult) row.getItem()).getDTO().getPerson());
                    }
                    else if(row.getItem() instanceof ObservablePediatrist) {
                        AccessorSceneManager.loadAllergies(((ObservablePediatrist) row.getItem()).getDTO().getPerson());
                    }
                    else if(row.getItem() instanceof ObservableStaff) {
                        AccessorSceneManager.loadAllergies(((ObservableStaff) row.getItem()).getDTO().getPerson());
                    }
                    else if(row.getItem() instanceof ObservableSupplier) {
                        AccessorSceneManager.loadAllergies(((ObservableSupplier) row.getItem()).getDTO().getPerson());
                    }
                } catch (IOException ex) {
                    System.err.println("can't load allergies management window");
                    ex.printStackTrace();
                }
                refreshTable();
            });
            contextMenu.getItems().add(allergiesManagement);

            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu)
            );

            return row;
        });
    }
}
