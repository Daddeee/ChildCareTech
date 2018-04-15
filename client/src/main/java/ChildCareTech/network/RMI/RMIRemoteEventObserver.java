package ChildCareTech.network.RMI;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.RemoteEventObserver;

public class RMIRemoteEventObserver implements RemoteEventObserver {
    @Override
    public void update(EventDTO eventDTO) {
        System.out.println(eventDTO.getName());
    }
}
