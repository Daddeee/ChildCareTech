package ChildCareTech.utils;

import ChildCareTech.common.DTO.TripPartecipationDTO;

public class TripPartecipationData {
    private String fiscalCode;
    private String firstName;
    private String lastName;
    private String licensePlate;

    public TripPartecipationData(String fiscalCode, String firstName, String lastName, String licensePlate){
        this.fiscalCode = fiscalCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.licensePlate = licensePlate;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public static TripPartecipationData buildFromDTO(TripPartecipationDTO tripPartecipationDTO){
        return new TripPartecipationData(
                tripPartecipationDTO.getPerson().getFiscalCode(),
                tripPartecipationDTO.getPerson().getFirstName(),
                tripPartecipationDTO.getPerson().getLastName(),
                tripPartecipationDTO.getBus().getLicensePlate()
        );
    }
}
