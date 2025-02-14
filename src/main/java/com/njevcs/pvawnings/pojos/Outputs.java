/**
 * 
 */
package com.njevcs.pvawnings.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author patel
 *
 * Nov 19, 2024
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Outputs {

    private OutputsMetadata avg_dni;

    private OutputsMetadata avg_ghi;

    private OutputsMetadata avg_lat_tilt;

    /**
     * @return the avg_dni
     */
    public OutputsMetadata getAvg_dni() {
        return avg_dni;
    }

    /**
     * @param avg_dni the avg_dni to set
     */
    public void setAvg_dni(OutputsMetadata avg_dni) {
        this.avg_dni = avg_dni;
    }

    /**
     * @return the avg_ghi
     */
    public OutputsMetadata getAvg_ghi() {
        return avg_ghi;
    }

    /**
     * @param avg_ghi the avg_ghi to set
     */
    public void setAvg_ghi(OutputsMetadata avg_ghi) {
        this.avg_ghi = avg_ghi;
    }

    /**
     * @return the avg_lat_tilt
     */
    public OutputsMetadata getAvg_lat_tilt() {
        return avg_lat_tilt;
    }

    /**
     * @param avg_lat_tilt the avg_lat_tilt to set
     */
    public void setAvg_lat_tilt(OutputsMetadata avg_lat_tilt) {
        this.avg_lat_tilt = avg_lat_tilt;
    }

}
