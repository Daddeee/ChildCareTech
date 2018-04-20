package ChildCareTech.utils;

import ChildCareTech.common.DTO.TripPartecipationDTO;

public class TripPartecipationData {
    private TripPartecipationDTO tripPartecipationDTO;
    private String fiscalCode;
    private String firstName;
    private String lastName;
    private String licensePlate;

    public TripPartecipationData(TripPartecipationDTO tripPartecipationDTO, String fiscalCode, String firstName, String lastName, String licensePlate){
        this.tripPartecipationDTO = tripPartecipationDTO;
        this.fiscalCode = fiscalCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.licensePlate = licensePlate;
    }

    public TripPartecipationDTO getTripPartecipationDTO() {
        return tripPartecipationDTO;
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

    public void setTripPartecipationDTO(TripPartecipationDTO tripPartecipationDTO) {
        this.tripPartecipationDTO = tripPartecipationDTO;
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
                tripPartecipationDTO,
                tripPartecipationDTO.getPerson().getFiscalCode(),
                tripPartecipationDTO.getPerson().getFirstName(),
                tripPartecipationDTO.getPerson().getLastName(),
                tripPartecipationDTO.getBus().getLicensePlate()
        );
    }
}
