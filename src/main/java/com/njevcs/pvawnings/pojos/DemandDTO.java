/**
 * 
 */
package com.njevcs.pvawnings.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author patel
 *
 *         Feb 5, 2025
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public interface DemandDTO {

    String getCity();

    String getCounty();

    Integer getTotalEvs();

    Integer getAvgIncome();

    Integer getPublicLevel1();

    Integer getPublicLevel2();

    Integer getPublicDcFast();

    Integer getPrivateLevel1();

    Integer getPrivateLevel2();

    Integer getPrivateDcFast();

    Integer getTotalSolarKwhPerDay();
}
