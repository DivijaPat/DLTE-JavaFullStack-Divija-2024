package com.mybank.dao.insurance.entity;

public class InsuranceAvailable {
    private Integer insuranceId;
    private String insuranceType;
    private String insuranceName;
    private String insuranceKeyBenefits;
    private Integer insuranceLifetime;

    public InsuranceAvailable(Integer insuranceId, String insuranceType, String insuranceName, String insuranceKeyBenefits, Integer insuranceLifetime) {
        this.insuranceId = insuranceId;
        this.insuranceType = insuranceType;
        this.insuranceName = insuranceName;
        this.insuranceKeyBenefits = insuranceKeyBenefits;
        this.insuranceLifetime = insuranceLifetime;
    }

    public InsuranceAvailable() {
    }
    public Integer getInsuranceId() {
        return insuranceId;
    }
    public void setInsuranceId(Integer insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getInsuranceKeyBenefits() {
        return insuranceKeyBenefits;
    }

    public Integer getInsuranceLifetime() {
        return insuranceLifetime;
    }

    public String getInsuranceType() {
        return insuranceType;
    }
    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }
    public String getInsuranceName() {
        return insuranceName;
    }
    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }
    public void setInsuranceKeyBenefits(String insuranceKeyBenefits) {
        this.insuranceKeyBenefits = insuranceKeyBenefits;
    }
    public void setInsuranceLifetime(Integer insuranceLifetime) {
        this.insuranceLifetime = insuranceLifetime;
    }
}
