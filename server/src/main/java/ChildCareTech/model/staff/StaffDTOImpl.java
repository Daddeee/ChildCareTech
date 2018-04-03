package ChildCareTech.model.staff;

import ChildCareTech.common.DTO.StaffDTO;
import ChildCareTech.model.adult.AdultDTOImpl;

public class StaffDTOImpl extends AdultDTOImpl implements StaffDTO {
    public StaffDTOImpl(Staff staff) { super(staff); }
}