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
public class Inputs {

    private String lat;

    private String lon;

    private String address;

    /**
     * @return the lat
     */
    public String getLat() {
        return lat;
    }

    /**
     * @param lat the lat to set
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     * @return the lon
     */
    public String getLon() {
        return lon;
    }

    /**
     * @param lon the lon to set
     */
    public void setLon(String lon) {
        this.lon = lon;
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

}
