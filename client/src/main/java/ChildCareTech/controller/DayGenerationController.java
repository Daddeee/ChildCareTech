package ChildCareTech.controller;

import ChildCareTech.common.DTO.DayGenerationSettingsDTO;
import ChildCareTech.services.MainWindowService;
import ChildCareTech.services.SessionService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.time.LocalTime;

public class DayGenerationController implements MainWindowControllerInterface {
    @FXML protected TextField weekNumberField;
    @FXML protected TextField entryTimeField;
    @FXML protected TextField exitTimeField;

    @FXML protected RadioButton lunHoliday;
    @FXML protected RadioButton marHoliday;
    @FXML protected RadioButton merHoliday;
    @FXML protected RadioButton gioHoliday;
    @FXML protected RadioButton venHoliday;
    @FXML protected RadioButton sabHoliday;
    @FXML protected RadioButton domHoliday;

    private MainWindowService mainWindowService;

    public void saveButtonAction(ActionEvent e){
        DayGenerationSettingsDTO settings = new DayGenerationSettingsDTO(
                Integer.parseInt(weekNumberField.getText()),
                LocalTime.parse(entryTimeField.getText()),
                LocalTime.parse(exitTimeField.getText())
        );

        settings.setMondayHoliday(lunHoliday.isSelected());
        settings.setTuesdayHoliday(marHoliday.isSelected());
        settings.setWednesdayHoliday(merHoliday.isSelected());
        settings.setThursdayHoliday(gioHoliday.isSelected());
        settings.setFridayHoliday(venHoliday.isSelected());
        settings.setSaturdayHoliday(sabHoliday.isSelected());
        settings.setSundayHoliday(domHoliday.isSelected());

        try{
            SessionService.getSession().generateDays(settings);
            SessionService.getSession().setFirstEverStartup(false);

            mainWindowService.loadMainWindow();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void setMainWindowService(MainWindowService mainWindowService) {
        this.mainWindowService = mainWindowService;
    }
}
