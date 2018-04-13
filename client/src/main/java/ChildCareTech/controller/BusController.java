package ChildCareTech.controller;

import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.services.AccessorSceneManager;
import ChildCareTech.services.ObservableDTOs.ObservableBus;
import ChildCareTech.services.SessionService;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BusController extends AbstractGenericAnagraphicController<ObservableBus> {

    public void initialize(){
        Map<String, String> properties = ObservableBus.getProperties();
        init(properties);
    }

    @Override
    protected void addButtonAction(ActionEvent e) {
        try {
            AccessorSceneManager.loadAddBus();
        } catch (IOException ex) {
            System.err.println("Can't load showTrip window");
            ex.printStackTrace();
        }
    }

    @Override
    protected List<ObservableBus> getItems() {
        List<BusDTO> busesDTOList;
        List<ObservableBus> observableBusesDTOList = new ArrayList<>();

        try {
            busesDTOList = SessionService.getSession().getAllBuses();
        } catch(RemoteException e){
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }

        for(BusDTO busDTO : busesDTOList)
            observableBusesDTOList.add(new ObservableBus(busDTO));

        return observableBusesDTOList;
    }

    @Override
    public void show(ObservableBus observableBus) {
        try {
            AccessorSceneManager.loadShowBus(observableBus);
        } catch (IOException ex) {
            System.err.println("Can't load showTrip window");
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(ObservableBus observableBus) {
        try {
            SessionService.getSession().removeBus(observableBus.getDTO());
        } catch (RemoteException ex) {
            System.err.println("error remote");
            ex.printStackTrace();
        }
    }

    @Override
    public void update(ObservableBus observableBus) {
        try {
            AccessorSceneManager.loadUpdateBus(observableBus);
        } catch (IOException ex) {
            System.err.println("Can't load showTrip window");
            ex.printStackTrace();
        }
    }
}
