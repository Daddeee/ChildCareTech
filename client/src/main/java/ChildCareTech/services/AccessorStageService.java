package ChildCareTech.services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AccessorStageService {

    private static Stage accessorStage;

    private AccessorStageService() { }

    public static Stage getStage() throws NoSuchFieldException {
        if(accessorStage == null)
            throw new NoSuchFieldException("Stage not initialized");
        return accessorStage;
    }

    public static void init() {
        if(accessorStage == null)
            accessorStage = new Stage();
        accessorStage.initModality(Modality.APPLICATION_MODAL);
        accessorStage.setResizable(false);
    }

    public static void setStage(Stage stage) {
        accessorStage = stage;
    }

    public static void close()  throws NoSuchFieldException {
        if(accessorStage == null)
            throw new NoSuchFieldException("Stage not initialized");
        accessorStage.close();
    }

    public static void show()  throws NoSuchFieldException {
        if(accessorStage == null)
            throw new NoSuchFieldException("Stage not initialized");
        accessorStage.show();
    }

    public static void setScene(Scene scene)  throws NoSuchFieldException {
        if(accessorStage == null)
            throw new NoSuchFieldException("Stage not initialized");
        accessorStage.setScene(scene);
    }

    public static void setTitle(String title)  throws NoSuchFieldException {
        if(accessorStage == null)
            throw new NoSuchFieldException("Stage not initialized");
        accessorStage.setTitle(title);
    }

    public static void setResizable(boolean bool)  throws NoSuchFieldException {
        if(accessorStage == null)
            throw new NoSuchFieldException("Stage not initialized");
        accessorStage.setResizable(bool);
    }

    public static void hide()  throws NoSuchFieldException {
        if(accessorStage == null)
            throw new NoSuchFieldException("Stage not initialized");
        accessorStage.hide();
    }

    public static double getHeight()  throws NoSuchFieldException {
        if(accessorStage == null)
            throw new NoSuchFieldException("Stage not initialized");
        return accessorStage.getHeight();
    }

    public static double getWidth()  throws NoSuchFieldException {
        if(accessorStage == null)
            throw new NoSuchFieldException("Stage not initialized");
        return accessorStage.getWidth();
    }

    public static void changeScene(Scene scene)  throws NoSuchFieldException {
        if(accessorStage == null)
            throw new NoSuchFieldException("Stage not initialized");
        AccessorStageService.hide();
        AccessorStageService.setScene(scene);
        AccessorStageService.show();
    }

    public static void changeScene(String fxmlPath, String cssPath) throws IOException, NoSuchFieldException {
        if(accessorStage == null)
            throw new NoSuchFieldException("Stage not initialized");
        Parent root = FXMLLoader.load(AccessorStageService.class.getResource(fxmlPath));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(cssPath);
        AccessorStageService.changeScene(scene);
    }
}
