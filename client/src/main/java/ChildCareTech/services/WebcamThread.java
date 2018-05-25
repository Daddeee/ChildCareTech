package ChildCareTech.services;

import ChildCareTech.utils.NewWebcamQRCodeReader;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import javafx.application.Platform;

public class WebcamThread extends Thread {

    private NewWebcamQRCodeReader controller;
    private Webcam webcam;
    private WebcamResolution webcamResolution;

    public WebcamThread(NewWebcamQRCodeReader controller, WebcamResolution webcamResolution) {
        this.controller = controller;
        this.webcamResolution = webcamResolution;
    }

    @Override
    public void run() {
        webcam = Webcam.getDefault();
        webcam.setViewSize(webcamResolution.getSize());
        Platform.runLater(() -> controller.setWebcam(webcam));
    }

}
