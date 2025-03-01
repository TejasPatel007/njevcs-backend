/**
 * 
 */
package com.njevcs.pvawnings.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.njevcs.pvawnings.pojos.DemandCalculationDTO;
import com.njevcs.pvawnings.pojos.DemandDTO;
import com.njevcs.pvawnings.repository.DemandRepository;
import com.njevcs.pvawnings.utils.Utility;

/**
 * 
 * @author patel
 *
 *         Feb 5, 2025
 */
@Service
public class DemandService {

    @Autowired
    private DemandRepository demandRepository;

    @Autowired
    @Lazy
    private DemandService self;

    @Cacheable(value = "demandCity", key = "#city")
    public DemandCalculationDTO getDemandByCity(String city) {
        DemandDTO demandByCity = demandRepository.getDetailsForCity(city);
        if (Objects.nonNull(demandByCity)) {
            return Utility.calculateDemand(new DemandCalculationDTO(demandByCity));
        } else {
            return null;
        }
    }

    @Cacheable(value = "demandCities")
    public List<DemandCalculationDTO> getDemandForAllCities() {
        List<DemandDTO> demandForAllCities = demandRepository.getDetailsForAllCities();
        if (!CollectionUtils.isEmpty(demandForAllCities)) {
            List<DemandCalculationDTO> dtos = new ArrayList<>();
            demandForAllCities.stream().forEach(demandForCity -> dtos.add(Utility.calculateDemand(new DemandCalculationDTO(demandForCity))));
            return dtos;
        } else {
            return Collections.emptyList();
        }
    }

    @Cacheable(value = "top10Cities")
    public List<DemandCalculationDTO> getTop10EnergyDeficitCities() {
        List<DemandCalculationDTO> demandForAllCities = self.getDemandForAllCities();
        if (!CollectionUtils.isEmpty(demandForAllCities)) {
            List<DemandCalculationDTO> energyDeficitCities =
                    demandForAllCities.stream().filter(demand -> demand.getExcessEnergy() < 0).collect(Collectors.toList());
            return energyDeficitCities.stream().sorted(Comparator.comparingInt(DemandCalculationDTO::getExcessEnergy)).limit(10)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    @Cacheable(value = "top10CitiesForCounty", key = "#county")
    public List<DemandCalculationDTO> getTop10EnergyDeficitCitiesForCounty(String county) {
        List<DemandCalculationDTO> demandForAllCities = self.getDemandForAllCities();
        if (!CollectionUtils.isEmpty(demandForAllCities)) {
            List<DemandCalculationDTO> energyDeficitCities = demandForAllCities.stream()
                    .filter(demand -> county.equals(demand.getCounty()) && demand.getExcessEnergy() < 0).collect(Collectors.toList());
            return energyDeficitCities.stream().sorted(Comparator.comparingInt(DemandCalculationDTO::getExcessEnergy)).limit(10)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    @Cacheable(value = "demandCounty", key = "#county")
    public DemandCalculationDTO getDemandByCounty(String county) {
        DemandDTO demandByCounty = demandRepository.getDetailsForCounty(county);
        if (Objects.nonNull(demandByCounty)) {
            return Utility.calculateDemand(new DemandCalculationDTO(demandByCounty));
        } else {
            return null;
        }
    }

    @Cacheable(value = "demandCounties")
    public List<DemandCalculationDTO> getDemandForAllCounties() {
        List<DemandDTO> demandForAllCounties = demandRepository.getDetailsForAllCounties();
        if (!CollectionUtils.isEmpty(demandForAllCounties)) {
            List<DemandCalculationDTO> dtos = new ArrayList<>();
            demandForAllCounties.stream().forEach(demandForCounty -> dtos.add(Utility.calculateDemand(new DemandCalculationDTO(demandForCounty))));
            return dtos;
        } else {
            return Collections.emptyList();
        }
    }

    @Cacheable(value = "top10Counties")
    public List<DemandCalculationDTO> getTop10EnergyDeficitCounties() {
        List<DemandCalculationDTO> demandForAllCounties = self.getDemandForAllCounties();
        if (!CollectionUtils.isEmpty(demandForAllCounties)) {
            List<DemandCalculationDTO> energyDeficitCounties =
                    demandForAllCounties.stream().filter(demand -> demand.getExcessEnergy() < 0).collect(Collectors.toList());
            return energyDeficitCounties.stream().sorted(Comparator.comparingInt(DemandCalculationDTO::getExcessEnergy)).limit(10)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

}
