package com.mybank.dao.insurance.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class InsuranceAvailed {
    private int insuranceAvailedId;
    private Integer customerId;
    private Integer insuranceId;
    @NotNull(message = "{availed.insuranceCoverage.null}")
    @Digits(integer = Integer.MAX_VALUE, fraction = Integer.MAX_VALUE, message = "{availed.insuranceCoverage.invalid}")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Double insuranceCoverage;
    private Double insurancePremium;
    private String insuranceType;
    private String insuranceName;
    private String insuranceKeyBenefits;
    private Integer insuranceLifetime;

    public InsuranceAvailed(int insuranceAvailedId, Integer customerId, Integer insuranceId, Double insuranceCoverage, Double insurancePremium, String insuranceType, String insuranceName, String insuranceKeyBenefits, Integer insuranceLifetime) {
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

    public int getInsuranceAvailedId() {
        return insuranceAvailedId;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
