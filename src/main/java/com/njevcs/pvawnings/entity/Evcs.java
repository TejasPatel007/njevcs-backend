package com.njevcs.pvawnings.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ColumnDefault;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.njevcs.pvawnings.utils.ConnectorType;
import com.njevcs.pvawnings.utils.Constants;
import io.swagger.annotations.ApiModel;

/**
 * 
 * @author patel
 *
 *         Nov 14, 2024
 */
@Entity
@Table(name = "evcs")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@ApiModel(description = "Represents an electric vehicle charger station entity in the system")
@JsonIgnoreProperties(value = {"public"})
public class Evcs implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 999199589968462431L;

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

    @Column(name = ConnectorType.Constants.NEMA1450_VALUE)
    private boolean NEMA1450;

    @Column(name = ConnectorType.Constants.NEMA515_VALUE)
    private boolean NEMA515;

    @Column(name = ConnectorType.Constants.NEMA520_VALUE)
    private boolean NEMA520;

    @Column(name = ConnectorType.Constants.J1772_VALUE)
    private boolean j1772;

    @Column(name = ConnectorType.Constants.J1772COMBO_VALUE)
    private boolean j1772COMBO;

    @Column(name = ConnectorType.Constants.CHADEMO_VALUE)
    private boolean CHADEMO;

    @Column(name = ConnectorType.Constants.TESLA_VALUE)
    private boolean TESLA;

    @Column(name = "dc_fast_points")
    @ColumnDefault(Constants.ZERO)
    private Integer dcFastPoints;

    @Column(name = "level1_points")
    @ColumnDefault(Constants.ZERO)
    private Integer level1Points;

    @Column(name = "level2_points")
    @ColumnDefault(Constants.ZERO)
    private Integer level2Points;

    @Column(name = "total_points")
    @ColumnDefault(Constants.ZERO)
    private Integer totalPoints;

    @JsonProperty("isPublic")
    private boolean isPublic;

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
     * @return the nEMA1450
     */
    public boolean isNEMA1450() {
        return NEMA1450;
    }

    /**
     * @param nEMA1450 the nEMA1450 to set
     */
    public void setNEMA1450(boolean nEMA1450) {
        NEMA1450 = nEMA1450;
    }

    /**
     * @return the nEMA515
     */
    public boolean isNEMA515() {
        return NEMA515;
    }

    /**
     * @param nEMA515 the nEMA515 to set
     */
    public void setNEMA515(boolean nEMA515) {
        NEMA515 = nEMA515;
    }

    /**
     * @return the nEMA520
     */
    public boolean isNEMA520() {
        return NEMA520;
    }

    /**
     * @param nEMA520 the nEMA520 to set
     */
    public void setNEMA520(boolean nEMA520) {
        NEMA520 = nEMA520;
    }

    /**
     * @return the j1772
     */
    public boolean isJ1772() {
        return j1772;
    }

    /**
     * @param j1772value the j1772 to set
     */
    public void setJ1772(boolean j1772value) {
        j1772 = j1772value;
    }

    /**
     * @return the j1772COMBO
     */
    public boolean isJ1772COMBO() {
        return j1772COMBO;
    }

    /**
     * @param j1772combo the j1772COMBO to set
     */
    public void setJ1772COMBO(boolean j1772combo) {
        j1772COMBO = j1772combo;
    }

    /**
     * @return the cHADEMO
     */
    public boolean isCHADEMO() {
        return CHADEMO;
    }

    /**
     * @param cHADEMO the cHADEMO to set
     */
    public void setCHADEMO(boolean cHADEMO) {
        CHADEMO = cHADEMO;
    }

    /**
     * @return the tESLA
     */
    public boolean isTESLA() {
        return TESLA;
    }

    /**
     * @param tESLA the tESLA to set
     */
    public void setTESLA(boolean tESLA) {
        TESLA = tESLA;
    }

    /**
     * @return the dcFastPoints
     */
    public Integer getDcFastPoints() {
        if (Objects.isNull(dcFastPoints))
            dcFastPoints = 0;
        return dcFastPoints;
    }

    /**
     * @param dcFastPoints the dcFastPoints to set
     */
    public void setDcFastPoints(Integer dcFastPoints) {
        this.dcFastPoints = dcFastPoints;
    }

    /**
     * @return the level1Points
     */
    public Integer getLevel1Points() {
        if (Objects.isNull(level1Points))
            level1Points = 0;
        return level1Points;
    }

    /**
     * @param level1Points the level1Points to set
     */
    public void setLevel1Points(Integer level1Points) {
        this.level1Points = level1Points;
    }

    /**
     * @return the level2Points
     */
    public Integer getLevel2Points() {
        if (Objects.isNull(level2Points))
            level2Points = 0;
        return level2Points;
    }

    /**
     * @param level2Points the level2Points to set
     */
    public void setLevel2Points(Integer level2Points) {
        this.level2Points = level2Points;
    }

    /**
     * @return the totalPoints
     */
    public Integer getTotalPoints() {
        if (Objects.isNull(totalPoints))
            totalPoints = 0;
        return totalPoints;
    }

    /**
     * @param totalPoints the totalPoints to set
     */
    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    /**
     * @return the isPublic
     */
    public boolean isPublic() {
        return isPublic;
    }

    /**
     * @param isPublic the isPublic to set
     */
    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

}
