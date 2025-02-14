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
@Table(name = "economy")
@ApiModel(description = "Represents an economy entity in the system")
public class Economy implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4187804809184111121L;

    @Id
    @Column(name = "zip_code")
    private String zipCode;

    private Integer income;

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
     * @return the income
     */
    public Integer getIncome() {
        return income;
    }

    /**
     * @param income the income to set
     */
    public void setIncome(Integer income) {
        this.income = income;
    }

}
