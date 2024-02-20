package basics.service;

public class Bond {
    private Integer maturityAmount;
    private Double rateOfInterest;
    private String taxStatus;
    private Integer tenure;
    private String bondHolder;
    public Bond(Integer maturityAmount,Double rateOfInterest,String taxStatus,String bondHolder,Integer tenure){
        this.maturityAmount=maturityAmount;
        this.rateOfInterest=rateOfInterest;
        this.taxStatus=taxStatus;
        this.bondHolder=bondHolder;
        this.tenure=tenure;
    }

    public Integer getMaturityAmount() {
        return maturityAmount;
    }

    public Double getRateOfInterest() {
        return rateOfInterest;
    }

    public String getTaxStatus() {
        return taxStatus;
    }

    public String getBondHolder() {
        return bondHolder;
    }

    public Integer getTenure() {
        return tenure;
    }

    public void setMaturityAmount(Integer maturityAmount){
        this.maturityAmount=maturityAmount;
    }

    public void setRateOfInterest(Double rateOfInterest) {
        this.rateOfInterest = rateOfInterest;
    }

    public void setTaxStatus(String taxStatus) {
        this.taxStatus = taxStatus;
    }

    public void setBondHolder(String bondHolder) {
        this.bondHolder = bondHolder;
    }

    public void setTenure(Integer tenure) {
        this.tenure = tenure;
    }
}
