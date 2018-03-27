package ChildCareTech.services;

import java.io.IOException;

public class SceneManager {
    private SceneManager() { }
    public static void loadLogin() throws IOException{
        StageService.changeScene("/view/loginWindow.fxml", "/style/loginWindow.css");
    }
    public static void loadHome() throws IOException{
        StageService.changeScene("/view/homeWindow.fxml", "/style/homeWindow.css");
    }
    public static void loadAnagraphics() throws IOException{
        StageService.changeScene("/view/anagraphicsWindow.fxml", "/style/anagraphicsWindow.css");
    }

}
