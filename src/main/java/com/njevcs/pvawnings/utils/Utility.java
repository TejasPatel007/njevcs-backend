/**
 * 
 */
package com.njevcs.pvawnings.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.util.CollectionUtils;
import com.njevcs.pvawnings.pojos.DemandCalculationDTO;

/**
 * @author patel
 *
 *         Dec 1, 2024
 */
public class Utility {

    public static String getIncomeLevel(Integer income) {
        if (income < Constants.LOW_INCOME_THRESHOLD) {
            return Constants.LOW;
        } else if (income >= Constants.LOW_INCOME_THRESHOLD && income < Constants.HIGH_INCOME_THRESHOLD) {
            return Constants.MEDIUM;
        } else if (income >= Constants.HIGH_INCOME_THRESHOLD) {
            return Constants.HIGH;
        } else {
            return Constants.LOW;
        }
    }

    public static Integer getIntAverage(List<Integer> numbers) {

        Integer sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }

        return sum / numbers.size();
    }

    public static Map<String, Object> generateResponse(boolean isList, List<Map<String, Object>> listOfMap, Map<String, Object> map,
            String errorMessage) {
        Map<String, Object> response = new HashMap<>();
        if (isList) {
            response.put(Constants.DATA, listOfMap);
            response.put(Constants.TOTAL, !CollectionUtils.isEmpty(listOfMap) ? listOfMap.size() : 0);
            response.put(Constants.ERROR_MESSAGE, !CollectionUtils.isEmpty(listOfMap) ? null : errorMessage);
        } else {
            response.put(Constants.DATA, map);
            response.put(Constants.TOTAL, !CollectionUtils.isEmpty(map) ? 1 : 0);
            response.put(Constants.ERROR_MESSAGE, !CollectionUtils.isEmpty(map) ? null : errorMessage);
        }
        return response;
    }

    public static Map<String, Object> generateResponse(boolean isList, List<DemandCalculationDTO> listOfDto, DemandCalculationDTO dto,
            String errorMessage) {
        Map<String, Object> response = new HashMap<>();
        if (isList) {
            response.put(Constants.DATA, !CollectionUtils.isEmpty(listOfDto) ? listOfDto : new HashMap<>());
            response.put(Constants.TOTAL, !CollectionUtils.isEmpty(listOfDto) ? listOfDto.size() : 0);
            response.put(Constants.ERROR_MESSAGE, !CollectionUtils.isEmpty(listOfDto) ? null : errorMessage);
        } else {
            response.put(Constants.DATA, Objects.nonNull(dto) ? dto : new HashMap<>());
            response.put(Constants.TOTAL, Objects.nonNull(dto) ? 1 : 0);
            response.put(Constants.ERROR_MESSAGE, Objects.nonNull(dto) ? null : errorMessage);
        }
        return response;
    }

    public static String getEvsLevel(Integer evs) {
        if (evs < 100) {
            return Constants.LOW;
        } else if (evs >= 100 && evs < 500) {
            return Constants.MEDIUM;
        } else if (evs >= 500) {
            return Constants.HIGH;
        } else {
            return Constants.LOW;
        }
    }

    public static String getCityEvsLevel(Integer evs) {
        if (evs < 250) {
            return Constants.LOW;
        } else if (evs >= 250 && evs < 700) {
            return Constants.MEDIUM;
        } else if (evs >= 700) {
            return Constants.HIGH;
        } else {
            return Constants.LOW;
        }
    }

    public static String getCountyEvsLevel(Integer evs) {
        if (evs < 4000) {
            return Constants.LOW;
        } else if (evs >= 4000 && evs < 10000) {
            return Constants.MEDIUM;
        } else if (evs >= 10000) {
            return Constants.HIGH;
        } else {
            return Constants.LOW;
        }
    }

    public static DemandCalculationDTO calculateDemand(DemandCalculationDTO dto) {

        // Determine public charging reliance based on income
        int publicChargingReliance;
        if (dto.getAvgIncome() <= Constants.LOW_INCOME_THRESHOLD) {
            publicChargingReliance = 70;
        } else if (dto.getAvgIncome() >= Constants.HIGH_INCOME_THRESHOLD) {
            publicChargingReliance = 20;
        } else {
            publicChargingReliance = 50;
        }

        // Compute total EV energy demand
        Integer totalEVEnergyDemand = dto.getTotalEvs() * Constants.EV_ENERGY_PER_DAY;

        // Compute public charging demand
        Integer publicChargingDemand = totalEVEnergyDemand * publicChargingReliance / 100;

        // Compute total annual public charging station capacity
        Integer chargingStationCapacity = ((dto.getPublicLevel1() * Constants.LEVEL1_POWER_KW * Constants.LEVEL1_UTILIZATION_HOURS
                * Constants.LEVEL1_UTILIZATION_CARS)
                + (dto.getPublicLevel2() * Constants.LEVEL2_POWER_KW * Constants.LEVEL2_UTILIZATION_HOURS * Constants.LEVEL2_UTILIZATION_CARS)
                + (dto.getPublicDcFast() * Constants.DC_FAST_POWER_KW * Constants.DC_FAST_UTILIZATION_HOURS * Constants.DC_FAST_UTILIZATION_CARS));

        // Compute unmet public charging demand
        Integer unmetEnergyDemand = Math.max(publicChargingDemand - chargingStationCapacity, 0);

        // Compute grid energy requirement
        Integer gridEnergyRequirement = Math.max(unmetEnergyDemand - dto.getTotalSolarKwhPerDay(), 0);

        Integer excessEnergy = dto.getTotalSolarKwhPerDay() - unmetEnergyDemand;

        dto.setTotalEVEnergyDemand(totalEVEnergyDemand);
        dto.setPublicChargingDemand(publicChargingDemand);
        dto.setChargingStationCapacity(chargingStationCapacity);
        dto.setUnmetEnergyDemand(unmetEnergyDemand);
        dto.setGridEnergyRequirement(gridEnergyRequirement);
        dto.setExcessEnergy(excessEnergy);

        return dto;
    }

}
