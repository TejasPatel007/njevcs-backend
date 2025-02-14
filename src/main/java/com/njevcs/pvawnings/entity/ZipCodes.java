package com.njevcs.pvawnings.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import io.swagger.annotations.ApiModel;

/**
 * 
 * @author patel
 *
 *         Nov 14, 2024
 */
@Entity
@Table(name = "zip_codes")
@ApiModel(description = "Represents a zip code entity in the system")
public class ZipCodes implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4738601643600840904L;

    @Id
    @Column(name = "zip_code")
    private String zipCode;

    private String city;

    private String county;

    /**
     * @return the zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
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
}
