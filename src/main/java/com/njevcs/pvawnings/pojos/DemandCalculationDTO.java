/**
 * 
 */
package com.njevcs.pvawnings.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.njevcs.pvawnings.utils.Utility;

/**
 * @author patel
 *
 *         Feb 5, 2025
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DemandCalculationDTO {

    private String city;

    private String county;

    private Integer totalEvs;

    private Integer avgIncome;

    private String incomeLevel;

    private Integer publicLevel1;

    private Integer publicLevel2;

    private Integer publicDcFast;

    private Integer privateLevel1;

    private Integer privateLevel2;

    private Integer privateDcFast;

    private Integer totalSolarKwhPerDay;

    private Integer totalEVEnergyDemand;

    private Integer publicChargingDemand;

    private Integer chargingStationCapacity;

    private Integer unmetEnergyDemand;

    private Integer gridEnergyRequirement;

    private Integer excessEnergy;

    public DemandCalculationDTO() {}

    /**
     * @param demandDTO
     */
    public DemandCalculationDTO(DemandDTO demandDTO) {
        super();
        this.city = demandDTO.getCity();
        this.county = demandDTO.getCounty();
        this.totalEvs = demandDTO.getTotalEvs();
        this.avgIncome = demandDTO.getAvgIncome();
        this.incomeLevel = Utility.getIncomeLevel(demandDTO.getAvgIncome());
        this.publicLevel1 = demandDTO.getPublicLevel1();
        this.publicLevel2 = demandDTO.getPublicLevel2();
        this.publicDcFast = demandDTO.getPublicDcFast();
        this.privateLevel1 = demandDTO.getPrivateLevel1();
        this.privateLevel2 = demandDTO.getPrivateLevel2();
        this.privateDcFast = demandDTO.getPrivateDcFast();
        this.totalSolarKwhPerDay = demandDTO.getTotalSolarKwhPerDay();
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the county
     */
    public String getCounty() {
        return county;
    }

    /**
     * @param county the county to set
     */
    public void setCounty(String county) {
        this.county = county;
    }

    /**
     * @return the totalEvs
     */
    public Integer getTotalEvs() {
        return totalEvs;
    }

    /**
     * @param totalEvs the totalEvs to set
     */
    public void setTotalEvs(Integer totalEvs) {
        this.totalEvs = totalEvs;
    }

    /**
     * @return the avgIncome
     */
    public Integer getAvgIncome() {
        return avgIncome;
    }

    /**
     * @param avgIncome the avgIncome to set
     */
    public void setAvgIncome(Integer avgIncome) {
        this.avgIncome = avgIncome;
    }

    /**
     * @return the incomeLevel
     */
    public String getIncomeLevel() {
        return incomeLevel;
    }

    /**
     * @param incomeLevel the incomeLevel to set
     */
    public void setIncomeLevel(String incomeLevel) {
        this.incomeLevel = incomeLevel;
    }

    /**
     * @return the publicLevel1
     */
    public Integer getPublicLevel1() {
        return publicLevel1;
    }

    /**
     * @param publicLevel1 the publicLevel1 to set
     */
    public void setPublicLevel1(Integer publicLevel1) {
        this.publicLevel1 = publicLevel1;
    }

    /**
     * @return the publicLevel2
     */
    public Integer getPublicLevel2() {
        return publicLevel2;
    }

    /**
     * @param publicLevel2 the publicLevel2 to set
     */
    public void setPublicLevel2(Integer publicLevel2) {
        this.publicLevel2 = publicLevel2;
    }

    /**
     * @return the publicDcFast
     */
    public Integer getPublicDcFast() {
        return publicDcFast;
    }

    /**
     * @param publicDcFast the publicDcFast to set
     */
    public void setPublicDcFast(Integer publicDcFast) {
        this.publicDcFast = publicDcFast;
    }

    /**
     * @return the privateLevel1
     */
    public Integer getPrivateLevel1() {
        return privateLevel1;
    }

    /**
     * @param privateLevel1 the privateLevel1 to set
     */
    public void setPrivateLevel1(Integer privateLevel1) {
        this.privateLevel1 = privateLevel1;
    }

    /**
     * @return the privateLevel2
     */
    public Integer getPrivateLevel2() {
        return privateLevel2;
    }

    /**
     * @param privateLevel2 the privateLevel2 to set
     */
    public void setPrivateLevel2(Integer privateLevel2) {
        this.privateLevel2 = privateLevel2;
    }

    /**
     * @return the privateDcFast
     */
    public Integer getPrivateDcFast() {
        return privateDcFast;
    }

    /**
     * @param privateDcFast the privateDcFast to set
     */
    public void setPrivateDcFast(Integer privateDcFast) {
        this.privateDcFast = privateDcFast;
    }

    /**
     * @return the totalSolarKwhPerDay
     */
    public Integer getTotalSolarKwhPerDay() {
        return totalSolarKwhPerDay;
    }

    /**
     * @param totalSolarKwhPerDay the totalSolarKwhPerDay to set
     */
    public void setTotalSolarKwhPerDay(Integer totalSolarKwhPerDay) {
        this.totalSolarKwhPerDay = totalSolarKwhPerDay;
    }

    /**
     * @return the totalEVEnergyDemand
     */
    public Integer getTotalEVEnergyDemand() {
        return totalEVEnergyDemand;
    }

    /**
     * @param totalEVEnergyDemand the totalEVEnergyDemand to set
     */
    public void setTotalEVEnergyDemand(Integer totalEVEnergyDemand) {
        this.totalEVEnergyDemand = totalEVEnergyDemand;
    }

    /**
     * @return the publicChargingDemand
     */
    public Integer getPublicChargingDemand() {
        return publicChargingDemand;
    }

    /**
     * @param publicChargingDemand the publicChargingDemand to set
     */
    public void setPublicChargingDemand(Integer publicChargingDemand) {
        this.publicChargingDemand = publicChargingDemand;
    }

    /**
     * @return the chargingStationCapacity
     */
    public Integer getChargingStationCapacity() {
        return chargingStationCapacity;
    }

    /**
     * @param chargingStationCapacity the chargingStationCapacity to set
     */
    public void setChargingStationCapacity(Integer chargingStationCapacity) {
        this.chargingStationCapacity = chargingStationCapacity;
    }

    /**
     * @return the unmetEnergyDemand
     */
    public Integer getUnmetEnergyDemand() {
        return unmetEnergyDemand;
    }

    /**
     * @param unmetEnergyDemand the unmetEnergyDemand to set
     */
    public void setUnmetEnergyDemand(Integer unmetEnergyDemand) {
        this.unmetEnergyDemand = unmetEnergyDemand;
    }

    /**
     * @return the gridEnergyRequirement
     */
    public Integer getGridEnergyRequirement() {
        return gridEnergyRequirement;
    }

    /**
     * @param gridEnergyRequirement the gridEnergyRequirement to set
     */
    public void setGridEnergyRequirement(Integer gridEnergyRequirement) {
        this.gridEnergyRequirement = gridEnergyRequirement;
    }

    /**
     * @return the excessEnergy
     */
    public Integer getExcessEnergy() {
        return excessEnergy;
    }

    /**
     * @param excessEnergy the excessEnergy to set
     */
    public void setExcessEnergy(Integer excessEnergy) {
        this.excessEnergy = excessEnergy;
    }

    @Override
    public String toString() {
        return "DemandCalculationDTO [city=" + city + ", county=" + county + ", totalEvs=" + totalEvs + ", avgIncome=" + avgIncome + ", publicLevel1="
                + publicLevel1 + ", publicLevel2=" + publicLevel2 + ", publicDcFast=" + publicDcFast + ", privateLevel1=" + privateLevel1
                + ", privateLevel2=" + privateLevel2 + ", privateDcFast=" + privateDcFast + ", totalSolarKwhPerDay=" + totalSolarKwhPerDay
                + ", totalEVEnergyDemand=" + totalEVEnergyDemand + ", publicChargingDemand=" + publicChargingDemand + ", chargingStationCapacity="
                + chargingStationCapacity + ", unmetEnergyDemand=" + unmetEnergyDemand + ", gridEnergyRequirement=" + gridEnergyRequirement
                + ", excessEnergy=" + excessEnergy + "]";
    }

}
