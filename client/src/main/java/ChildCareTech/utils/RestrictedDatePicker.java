package ChildCareTech.utils;

import ChildCareTech.services.SessionService;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

import java.time.LocalDate;

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
            minDate = SessionService.getSession().getMinSavedDate();
            maxDate = SessionService.getSession().getMaxSavedDate();
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