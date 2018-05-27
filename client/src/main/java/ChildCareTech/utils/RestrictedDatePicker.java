package ChildCareTech.utils;

import ChildCareTech.Client;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

import java.time.LocalDate;

/**
 * This class provides an extension of the {@link DatePicker DatePicker} class for restrict selectable
 * days to the days following the current day (current day included).
 */
public class RestrictedDatePicker extends DatePicker {
    private LocalDate minDate = LocalDate.MIN;
    private LocalDate maxDate = LocalDate.MAX;

    public RestrictedDatePicker() {
        super();
        retrieveDates();
        setDateRange();
    }

    public RestrictedDatePicker(LocalDate localDate) {
        super(localDate);
        retrieveDates();
        setDateRange();
    }

    public void retrieveDates(){
        try{
            minDate = Client.getSessionService().getSession().getMinSavedDate();
            maxDate = Client.getSessionService().getSession().getMaxSavedDate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setDateRange(){
        final Callback<DatePicker, DateCell> dayCellFactory = (final DatePicker datePicker) -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (item.isAfter(maxDate) || item.isBefore(minDate)) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        };

        this.setDayCellFactory(dayCellFactory);
    }
}