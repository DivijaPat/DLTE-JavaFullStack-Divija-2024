package com.mybank.dao.insurance.entity;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class InsuranceAvailed {
    @NotNull(message = "{availed.insuranceAvailedId.null}")
    @Digits(integer = 8, fraction = 0, message = "{user.customerId.null}")
    private Integer insuranceAvailedId;
    @NotNull(message = "{availed.customerId.null}")
    @Digits(integer = 8, fraction = 0, message = "{user.customerId.null}")
    private Integer customerId;
    @NotNull(message = "{availed.insuranceId.null}")
    @Digits(integer = 8, fraction = 0, message = "{user.customerId.null}")
    private Integer insuranceId;
    @NotNull(message = "{availed.insuranceCoverage.null}")
    @Digits(integer = Integer.MAX_VALUE, fraction = Integer.MAX_VALUE, message = "{availed.insuranceCoverage.invalid}")
    private Double insuranceCoverage;
    @NotNull(message = "{availed.insurancePremium.null}")
    @Digits(integer = Integer.MAX_VALUE, fraction = Integer.MAX_VALUE, message = "{availed.insuranceCoverage.invalid}")
    private Double insurancePremium;
    @NotNull(message = "{availed.insuranceType.null}")
    private String insuranceType;
    @NotNull(message = "{availed.insuranceName.null}")
    private String insuranceName;
    @NotNull(message = "{availed.insuranceKeyBenefits.null}")
    private String insuranceKeyBenefits;
    @NotNull(message = "{availed.insuranceLifetime.null}")
    @Digits(integer = 4, fraction = 0, message = "{user.customerId.null}")
    private Integer insuranceLifetime;



    public InsuranceAvailed(Integer insuranceAvailedId, Integer customerId, Integer insuranceId, Double insuranceCoverage, Double insurancePremium, String insuranceType, String insuranceName, String insuranceKeyBenefits, Integer insuranceLifetime) {
        this.insuranceAvailedId = insuranceAvailedId;
        this.customerId = customerId;
        this.insuranceId = insuranceId;
        this.insuranceCoverage = insuranceCoverage;
        this.insurancePremium = insurancePremium;
        this.insuranceType = insuranceType;
        this.insuranceName = insuranceName;
        this.insuranceKeyBenefits = insuranceKeyBenefits;
        this.insuranceLifetime = insuranceLifetime;
    }

    public InsuranceAvailed() {
    }

    public Integer getInsuranceAvailedId() {
        return insuranceAvailedId;
    }

    public void setInsuranceAvailedId(Integer insuranceAvailedId) {
        this.insuranceAvailedId = insuranceAvailedId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Integer insuranceId) {
        this.insuranceId = insuranceId;
    }

    public Double getInsuranceCoverage() {
        return insuranceCoverage;
    }

    public void setInsuranceCoverage(Double insuranceCoverage) {
        this.insuranceCoverage = insuranceCoverage;
    }

    public Double getInsurancePremium() {
        return insurancePremium;
    }

    public void setInsurancePremium(Double insurancePremium) {
        this.insurancePremium = insurancePremium;
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

    public String getInsuranceKeyBenefits() {
        return insuranceKeyBenefits;
    }

    public void setInsuranceKeyBenefits(String insuranceKeyBenefits) {
        this.insuranceKeyBenefits = insuranceKeyBenefits;
    }

    public Integer getInsuranceLifetime() {
        return insuranceLifetime;
    }

    public void setInsuranceLifetime(Integer insuranceLifetime) {
        this.insuranceLifetime = insuranceLifetime;
    }

    @Override
    public String toString() {
        return "InsuranceAvailed{" +
                "insuranceAvailedId=" + insuranceAvailedId +
                ", customerId=" + customerId +
                ", insuranceId=" + insuranceId +
                ", insuranceCoverage=" + insuranceCoverage +
                ", insurancePremium=" + insurancePremium +
                ", insuranceType='" + insuranceType + '\'' +
                ", insuranceName='" + insuranceName + '\'' +
                ", insuranceKeyBenefits='" + insuranceKeyBenefits + '\'' +
                ", insuranceLifetime=" + insuranceLifetime +
                '}';
    }
}
