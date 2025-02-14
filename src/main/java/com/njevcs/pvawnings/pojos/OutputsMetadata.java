/**
 * 
 */
package com.njevcs.pvawnings.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author patel
 *
 *         Nov 19, 2024
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OutputsMetadata {

    private Float annual;

    private OutputsMetadataMonthly monthly;

    /**
     * @return the annual
     */
    public Float getAnnual() {
        return annual;
    }

    /**
     * @param annual the annual to set
     */
    public void setAnnual(Float annual) {
        this.annual = annual;
    }

    /**
     * @return the monthly
     */
    public OutputsMetadataMonthly getMonthly() {
        return monthly;
    }

    /**
     * @param monthly the monthly to set
     */
    public void setMonthly(OutputsMetadataMonthly monthly) {
        this.monthly = monthly;
    }

}
