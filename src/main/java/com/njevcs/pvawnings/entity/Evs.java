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
@Table(name = "evs")
@ApiModel(description = "Represents an electric vehicle entity in the system")
public class Evs implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4778515122584162736L;

    @Id
    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "evs")
    private Integer numberOfEvs;

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
     * @return the numberOfEvs
     */
    public Integer getNumberOfEvs() {
        return numberOfEvs;
    }

    /**
     * @param numberOfEvs the numberOfEvs to set
     */
    public void setNumberOfEvs(Integer numberOfEvs) {
        this.numberOfEvs = numberOfEvs;
    }

}
