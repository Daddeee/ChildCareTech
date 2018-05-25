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

public class NewWebcamQRCodeReader extends Thread {

    private Result result;
    private Webcam webcam = null;
    private WebcamThread webcamThread;
    private WebcamPanel panel = null;
    private static boolean running;
    private CheckPointControllerInterface controller;

    public NewWebcamQRCodeReader(CheckPointControllerInterface controller) throws CameraBusyException {
        if(running)
            throw new CameraBusyException("camera already in use");
        this.controller = controller;

        this.running = true;
        webcamThread = new WebcamThread(this, WebcamResolution.QVGA);
        webcamThread.start();
    }

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
    public void setWebcam(Webcam webcam) {
        this.webcam = webcam;
        panel = new WebcamPanel(webcam);
        controller.setPane(panel);

        this.start();
    }
    public void shutDown() {
        if(webcam.isOpen()) webcam.close();
        running = false;
    }
}
