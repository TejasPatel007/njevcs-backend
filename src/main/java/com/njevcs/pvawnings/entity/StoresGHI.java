package com.njevcs.pvawnings.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import io.swagger.annotations.ApiModel;

/**
 * 
 * @author patel
 *
 *         Dec 4, 2024
 */
@Entity
@Table(name = "stores_ghi")
@ApiModel(description = "Represents a solar flux for stores entity in the system")
public class StoresGHI implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8944912992563493269L;

    @Id
    @Column(name = "stores_id")
    private Long storesId;

    @OneToOne
    @JoinColumn(name = "stores_id")
    @MapsId
    private Stores stores;

    private Float jan;

    private Float feb;

    private Float mar;

    private Float apr;

    private Float may;

    private Float jun;

    private Float jul;

    private Float aug;

    private Float sep;

    private Float oct;

    private Float nov;

    @Column(name = "\"dec\"")
    private Float dec;

    private Float annual;

    /**
     * @return the storesId
     */
    public Long getStoresId() {
        return storesId;
    }

    /**
     * @param storesId the storesId to set
     */
    public void setStoresId(Long storesId) {
        this.storesId = storesId;
    }

    /**
     * @param store the store to set
     */
    public void setStore(Stores store) {
        this.stores = store;
    }

    /**
     * @return the jan
     */
    public Float getJan() {
        return jan;
    }

    /**
     * @param jan the jan to set
     */
    public void setJan(Float jan) {
        this.jan = jan;
    }

    /**
     * @return the feb
     */
    public Float getFeb() {
        return feb;
    }

    /**
     * @param feb the feb to set
     */
    public void setFeb(Float feb) {
        this.feb = feb;
    }

    /**
     * @return the mar
     */
    public Float getMar() {
        return mar;
    }

    /**
     * @param mar the mar to set
     */
    public void setMar(Float mar) {
        this.mar = mar;
    }

    /**
     * @return the apr
     */
    public Float getApr() {
        return apr;
    }

    /**
     * @param apr the apr to set
     */
    public void setApr(Float apr) {
        this.apr = apr;
    }

    /**
     * @return the may
     */
    public Float getMay() {
        return may;
    }

    /**
     * @param may the may to set
     */
    public void setMay(Float may) {
        this.may = may;
    }

    /**
     * @return the jun
     */
    public Float getJun() {
        return jun;
    }

    /**
     * @param jun the jun to set
     */
    public void setJun(Float jun) {
        this.jun = jun;
    }

    /**
     * @return the jul
     */
    public Float getJul() {
        return jul;
    }

    /**
     * @param jul the jul to set
     */
    public void setJul(Float jul) {
        this.jul = jul;
    }

    /**
     * @return the aug
     */
    public Float getAug() {
        return aug;
    }

    /**
     * @param aug the aug to set
     */
    public void setAug(Float aug) {
        this.aug = aug;
    }

    /**
     * @return the sep
     */
    public Float getSep() {
        return sep;
    }

    /**
     * @param sep the sep to set
     */
    public void setSep(Float sep) {
        this.sep = sep;
    }

    /**
     * @return the oct
     */
    public Float getOct() {
        return oct;
    }

    /**
     * @param oct the oct to set
     */
    public void setOct(Float oct) {
        this.oct = oct;
    }

    /**
     * @return the nov
     */
    public Float getNov() {
        return nov;
    }

    /**
     * @param nov the nov to set
     */
    public void setNov(Float nov) {
        this.nov = nov;
    }

    /**
     * @return the dec
     */
    public Float getDec() {
        return dec;
    }

    /**
     * @param dec the dec to set
     */
    public void setDec(Float dec) {
        this.dec = dec;
    }

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

}
