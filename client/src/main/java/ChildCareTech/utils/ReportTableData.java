package ChildCareTech.utils;

import ChildCareTech.common.DTO.CheckpointDTO;
import ChildCareTech.common.DTO.KidDTO;

/**
 * This class is used for simplify the {@link CheckpointDTO CheckPointDTO} instance's display in tables.
 */
public class ReportTableData {
    private String fiscalCode;
    private String firstName;
    private String lastName;
    private String recordTime;

    public ReportTableData(String fiscalCode, String firstName, String lastName, String recordTime){
        this.fiscalCode = fiscalCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.recordTime = recordTime;
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

    public String getRecordTime() {
        return recordTime;
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

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public static ReportTableData buildFromDTOs(KidDTO kidDTO, CheckpointDTO checkpointDTO){
        return new ReportTableData(
                kidDTO.getPerson().getFiscalCode(),
                kidDTO.getPerson().getFirstName(),
                kidDTO.getPerson().getLastName(),
                checkpointDTO == null || checkpointDTO.getTime() == null ? "" : checkpointDTO.getTime().toString()
        );
    }
}
