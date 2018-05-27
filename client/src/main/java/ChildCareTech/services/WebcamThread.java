package ChildCareTech.services;

import ChildCareTech.utils.NewWebcamQRCodeReader;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import javafx.application.Platform;

/**
 *This class is a wrapper for the Sarxos' <a href = "https://github.com/sarxos/webcam-capture">webcam-capture library</a>.
 */

public class WebcamThread extends Thread {

    private NewWebcamQRCodeReader controller;
    private Webcam webcam;
    private WebcamResolution webcamResolution;

    /**
     * Create a WebcamThread with provided parameters.
     * <p>
     * Should be used to create a new thread that provide webcam access to the default device camera.
     * @param controller the NewWebcamQRCodeReader controller in which the instance of this class will call
     *                   the setWebcam method that sets the webcam instance variable.
     * @param webcamResolution the webcam resolution desired for the webcam stream (WebcamResolution instance).
     */
    public WebcamThread(NewWebcamQRCodeReader controller, WebcamResolution webcamResolution) {
        this.controller = controller;
        this.webcamResolution = webcamResolution;
    }

    /**
     * Gets the webcam instance for the default device's camera, sets the view size based on the webcamResolution.
     * Calls the NewWebcamQRCodeReader controller's setWebcam method.
     */
    @Override
    public void run() {
        webcam = Webcam.getDefault();
        webcam.setViewSize(webcamResolution.getSize());
        Platform.runLater(() -> controller.setWebcam(webcam));
    }

}
