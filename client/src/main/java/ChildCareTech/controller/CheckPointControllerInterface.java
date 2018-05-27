package ChildCareTech.controller;

import com.github.sarxos.webcam.WebcamPanel;

/**
 * This interface should be implemented by controllers that need acess to the
 * {@link ChildCareTech.utils.NewWebcamQRCodeReader WebcamQRCodeReader} thread.
 */
public interface CheckPointControllerInterface {
    /**
     * This method should manage the registration of a checkpoint.
     * @param code the fiscalcode with which the checkpoint registration is done.
     */
    void saveCheckPoint(String code);

    /**
     * This methods should manage to set the webcam's pane into the controller graphics.
     * @param webcamPanel the webcam pane.
     */
    void setPane(WebcamPanel webcamPanel);
}
