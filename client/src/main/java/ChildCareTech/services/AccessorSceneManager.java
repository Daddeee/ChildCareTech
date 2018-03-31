package ChildCareTech.services;

import java.io.IOException;

public class AccessorSceneManager {
    private AccessorSceneManager() { }

    public static void loadAddPerson() throws IOException {
        try {
            AccessorStageService.changeScene("/view/addPersonWindow.fxml", "style/addPersonWindow.css");
        } catch(NoSuchFieldException ex) {
            ex.printStackTrace();
        }
    }

    public static void loadRegisterUser() throws IOException {
        try {
            AccessorStageService.changeScene("/view/registerUserWindow.fxml", "style/registerUserWindow.css");
        } catch(NoSuchFieldException ex) {
            ex.printStackTrace();
        }
    }
}
