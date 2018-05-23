package ChildCareTech.services;

import ChildCareTech.controller.QRCodeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

public class QRCodeReaderSingleton {
    private static QRCodeReaderSingleton instance;
    private static boolean inUse = false;
    private static Node QRNode;
    private static FXMLLoader loader;
    private static QRCodeController controller;

    public static Node getQRNode() {
        if(inUse)
            return null;
        inUse = true;
        return QRNode;
    }
    public static QRCodeController getController() {
        return controller;
    }
    public static void reset() {
        inUse = false;
    }
    public static void init() throws IOException {
        instance = new QRCodeReaderSingleton();
        loader = new FXMLLoader(QRCodeReaderSingleton.class.getResource(ResourcesPaths.getQRCodeFXMLPath()));
        QRNode = loader.load();
        controller = loader.getController();
    }
    private QRCodeReaderSingleton() { }
}
