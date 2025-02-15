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
 *         Feb 15, 2025
 */
@Entity
@Table(name = "config")
@ApiModel(description = "All configurations required in the system")
public class Config implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -91004291484001992L;

    @Id
    @Column
    private String name;

    @Column
    private String value;

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
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

}
