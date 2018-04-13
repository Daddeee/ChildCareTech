package ChildCareTech.controller;

import ChildCareTech.services.MainSceneManager;
import ChildCareTech.services.ObservableDTOs.ObservableAnagraphic;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public abstract class AbstractGenericAnagraphicController<OBS extends ObservableAnagraphic> {
    @FXML
    protected TableView<OBS> itemsTable;

    protected ObservableList<OBS> items = FXCollections.observableArrayList();


    public void init(Map<String, String> properties){
        generateColumns(properties);
        generateContextMenu();
        refreshTable();
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

    protected void refreshTable(){
        items.clear();
        items.addAll(getItems());
        itemsTable.setItems(items);
    }

    protected void generateColumns(Map<String, String> properties) {
        for(Map.Entry<String, String> entry : properties.entrySet()){
            TableColumn<OBS, String> column = new TableColumn<>(entry.getKey());
            column.setMinWidth(100);
            column.setCellValueFactory(new PropertyValueFactory<OBS, String>(entry.getValue()));
            itemsTable.getColumns().add(column);
        }
    }

    protected void generateContextMenu(){
        itemsTable.setRowFactory(tripDTOTableView -> {
            final TableRow<OBS> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();

            final MenuItem showItem = new MenuItem("Dettagli");
            showItem.setOnAction(event ->  {
                contextMenu.hide();
                show(row.getItem());
            });
            contextMenu.getItems().add(showItem);

            final MenuItem deleteItem = new MenuItem("Elimina");
            deleteItem.setOnAction(event -> {
                contextMenu.hide();
                delete(row.getItem());
                refreshTable();
            });
            contextMenu.getItems().add(deleteItem);

            final MenuItem updateItem = new MenuItem("Modifica");
            updateItem.setOnAction(event -> {
                contextMenu.hide();
                update(row.getItem());
                refreshTable();
            });
            contextMenu.getItems().add(updateItem);

            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu)
            );

            return row;
        });
    }

    abstract public void initialize();

    abstract protected List<OBS> getItems();

    @FXML
    abstract protected void addButtonAction(ActionEvent e);

    abstract public void show(OBS obs);
    abstract public void delete(OBS obs);
    abstract public void update(OBS obs);
}
