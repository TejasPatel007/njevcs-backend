package com.njevcs.pvawnings.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;

/**
 * 
 * @author patel
 *
 *         Nov 14, 2024
 */
@Entity
@Table(name = "stores")
@ApiModel(description = "Represents a store entity in the system")
public class Stores implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8947082489686895127L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "zip_code")
    private ZipCodes zipCode;

    private String name;

    private String address;

    private Double latitude;

    private Double longitude;

    @Column(name = "parking_area")
    private Long parkingArea;

    @OneToOne(mappedBy = "stores", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private StoresGHI storesGHI;

    @Transient
    private Float solarPotential;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the zipCode
     */
    public ZipCodes getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(ZipCodes zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the parkingArea
     */
    public Long getParkingArea() {
        return parkingArea;
    }

    /**
     * @param parkingArea the parkingArea to set
     */
    public void setParkingArea(Long parkingArea) {
        this.parkingArea = parkingArea;
    }

    /**
     * @return the storesGHI
     */
    public StoresGHI getStoresGHI() {
        return storesGHI;
    }

    /**
     * @param storesGHI the storesGHI to set
     */
    public void setStoresGHI(StoresGHI storesGHI) {
        this.storesGHI = storesGHI;
    }

    /**
     * @return the solarPotential
     */
    public Float getSolarPotential() {
        if (Objects.isNull(storesGHI) || (Objects.isNull(storesGHI.getAnnual()))) {
            return 0.0F;
        }

        solarPotential = Math.round((Float.valueOf(parkingArea) * storesGHI.getAnnual() * 0.216F * 0.83F * 0.70F) * 100.0F) / 100.0F;
        // https://learn.arcgis.com/en/projects/estimate-solar-power-potential/#calculate-power-per-building:~:text=Usable_SR_MWh%20*%200.216%20*%200.83
        return solarPotential;
    }

}
