package ChildCareTech.controller;

import ChildCareTech.Client;
import ChildCareTech.common.DTO.DayGenerationSettingsDTO;
import ChildCareTech.services.MainWindowService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.time.LocalTime;

public class DayGenerationController implements MainWindowControllerInterface {
    @FXML protected TextField weekNumberField;
    @FXML private ComboBox<String> inComboBoxH;
    @FXML private ComboBox<String> outComboBoxH;
    @FXML private ComboBox<String> inComboBoxM;
    @FXML private ComboBox<String> outComboBoxM;

    private ObservableList<String> minutes = FXCollections.observableArrayList();
    private ObservableList<String> hour = FXCollections.observableArrayList();

    @FXML protected RadioButton lunHoliday;
    @FXML protected RadioButton marHoliday;
    @FXML protected RadioButton merHoliday;
    @FXML protected RadioButton gioHoliday;
    @FXML protected RadioButton venHoliday;
    @FXML protected RadioButton sabHoliday;
    @FXML protected RadioButton domHoliday;

    private MainWindowService mainWindowService;

    public void initialize() {
        initComboBoxes();
    }

    public void saveButtonAction(ActionEvent e){
        LocalTime entry = LocalTime.of(Integer.parseInt(inComboBoxH.getSelectionModel().getSelectedItem()), Integer.parseInt(inComboBoxM.getSelectionModel().getSelectedItem()));
        LocalTime exit = LocalTime.of(Integer.parseInt(outComboBoxH.getSelectionModel().getSelectedItem()), Integer.parseInt(outComboBoxM.getSelectionModel().getSelectedItem()));

        DayGenerationSettingsDTO settings = new DayGenerationSettingsDTO(
                Integer.parseInt(weekNumberField.getText()),
                entry,
                exit
        );

        settings.setMondayHoliday(lunHoliday.isSelected());
        settings.setTuesdayHoliday(marHoliday.isSelected());
        settings.setWednesdayHoliday(merHoliday.isSelected());
        settings.setThursdayHoliday(gioHoliday.isSelected());
        settings.setFridayHoliday(venHoliday.isSelected());
        settings.setSaturdayHoliday(sabHoliday.isSelected());
        settings.setSundayHoliday(domHoliday.isSelected());

        try{
            Client.getSessionService().getSession().generateDays(settings);
            Client.getSessionService().getSession().setFirstEverStartup(false);

            mainWindowService.loadMainWindow();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void setMainWindowService(MainWindowService mainWindowService) {
        this.mainWindowService = mainWindowService;
    }

    private void initComboBoxes() {
        minutes.clear();
        hour.clear();

        String number;
        for(Integer i = 0; i < 60; i++) {
            if(i<10)
                number = "0" + i.toString();
            else
                number = i.toString();
            minutes.add(number);
        }

        for(Integer i = 0; i< 24; i++) {
            if(i<10)
                number = "0" + i.toString();
            else
                number = i.toString();
            hour.add(number);
        }

        inComboBoxH.setItems(hour);
        outComboBoxH.setItems(hour);
        inComboBoxM.setItems(minutes);
        outComboBoxM.setItems(minutes);
    }
}
