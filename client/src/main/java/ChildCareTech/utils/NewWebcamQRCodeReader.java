package ChildCareTech.utils;

import ChildCareTech.common.exceptions.CameraBusyException;
import ChildCareTech.controller.CheckPointControllerInterface;
import ChildCareTech.services.WebcamThread;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
import javafx.application.Platform;

import java.awt.image.BufferedImage;

/**
 * This class provides a simple QR code reader (data stream and pane) using WebcamThread and implementing a workaround for MacOS iSight camera compatibility.
 */
public class NewWebcamQRCodeReader extends Thread {

    private Result result;
    private Webcam webcam = null;
    private WebcamThread webcamThread;
    private WebcamPanel panel = null;
    private static boolean running;
    private CheckPointControllerInterface controller;

    /**
     * This controller creates a new webcamThread and tried to get the default device's camera access.
     * (Default resolution is set to QVGA)
     * @param controller should be the controller that needs the access to the camera and manages the pane
     *                  that contains the camera.
     * @throws CameraBusyException in case the camera is already in use.
     */
    public NewWebcamQRCodeReader(CheckPointControllerInterface controller) throws CameraBusyException {
        if(running)
            throw new CameraBusyException("camera already in use");
        this.controller = controller;

        this.running = true;
        webcamThread = new WebcamThread(this, WebcamResolution.QVGA);
        webcamThread.start();
    }

    /**
     * If a valid QR code is read call the controller's saveCheckPoint method with the read string code
     * as parameter.
     * Should never be called from outside the class.
     */
    @Override
    public void run() {

        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {

                if ((image = webcam.getImage()) == null) {
                    continue;
                }

                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                try {
                    result = new MultiFormatReader().decode(bitmap);
                } catch (NotFoundException e) {
                    // fall thru, it means there is no QR code in image
                }
            }

            if (result != null) {
                Platform.runLater(() -> controller.saveCheckPoint(result.getText()));
            }

        } while (running);
        System.out.print("exiting");
    }

    /**
     *Store the webcam instance, creates a pane to contain it, calls the controller's setPane method with the pane as
     * parameter and starts the class' thread.
     * Should never be called except from WebcamThread.
     * @param webcam the webcam instance.
     */
    public void setWebcam(Webcam webcam) {
        this.webcam = webcam;
        panel = new WebcamPanel(webcam);
        controller.setPane(panel);

        this.start();
    }

    /**
     * Tries to shut the webcam thread down and stop the class' thread from run.
     * Should be called before closing the checkPoint controller to free the camera usage.
     */
    public void shutDown() {
        if(webcam.isOpen()) webcam.close();
        running = false;
    }
}
