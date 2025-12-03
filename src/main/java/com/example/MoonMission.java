package com.example;

import java.time.LocalDate;

public class MoonMission {
    private int missionId;
    private String spacecraft;
    private LocalDate launchDate;
    private String carrierRocket;
    private String operator;
    private String missionType;
    private String outcome;

    // Getters and setters
    public int getMissionId() {
        return missionId;
    }

    public String getSpacecraft() {
        return spacecraft;
    }
    public void setSpacecraft(String spacecraft) {
        this.spacecraft = spacecraft;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }
    public void setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
    }

    public String getCarrierRocket() {
        return carrierRocket;
    }
    public void setCarrierRocket(String carrierRocket) {
        this.carrierRocket = carrierRocket;
    }

    public String getOperator() {
        return operator;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getMissionType() {
        return missionType;
    }
    public void setMissionType(String missionType) {
        this.missionType = missionType;
    }

    public String getOutcome() {
        return outcome;
    }
    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }
}