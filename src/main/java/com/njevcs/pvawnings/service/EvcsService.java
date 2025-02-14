/**
 * 
 */
package com.njevcs.pvawnings.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.njevcs.pvawnings.entity.Evcs;
import com.njevcs.pvawnings.entity.ZipCodes;
import com.njevcs.pvawnings.repository.EvcsRepository;
import com.njevcs.pvawnings.utils.ConnectorType;
import com.njevcs.pvawnings.utils.Constants;

/**
 * @author patel
 *
 *         Nov 14, 2024
 */
@Service
public class EvcsService {

    @Autowired
    private EvcsRepository evcsRepository;

    @Autowired
    private ZipCodesService zipCodesService;

    public Evcs createEvcs(Evcs evcs) {
        evcs.setTotalPoints(evcs.getDcFastPoints() + evcs.getLevel1Points() + evcs.getLevel2Points());
        return evcsRepository.save(evcs);
    }

    public List<Evcs> getAllEvcs() {
        return evcsRepository.findAll();
    }

    public Evcs getEvcsById(Long id) {
        return evcsRepository.findById(id).orElse(null);
    }

    public List<Evcs> getEvcsByZip_Code(String zip_code) {
        return evcsRepository.findByZipCodeZipCode(zip_code);
    }

    @Cacheable(value = "evcsCity", key = "#city")
    public List<Evcs> getEvcsByCity(String city) {
        List<String> zipCodes = zipCodesService.getByCity(city).stream().map(ZipCodes::getZipCode).collect(Collectors.toList());
        return evcsRepository.findByZipCodeZipCodeIn(zipCodes);
    }

    @Cacheable(value = "evcsCounty", key = "#county")
    public List<Evcs> getEvcsByCounty(String county) {
        List<String> zipCodes = zipCodesService.getByCounty(county).stream().map(ZipCodes::getZipCode).collect(Collectors.toList());
        return evcsRepository.findByZipCodeZipCodeIn(zipCodes);
    }

    public List<Evcs> getEvcsByName(String name) {
        return evcsRepository.findByName(name);
    }

    public List<Evcs> getEvcsByZipCodeAndName(String zip_code, String name) {
        return evcsRepository.findByZipCodeZipCodeAndName(zip_code, name);
    }

    public List<Evcs> getEvcsByZipCodeAndAddress(String zip_code, String address) {
        return evcsRepository.findByZipCodeZipCodeAndAddress(zip_code, address);
    }

    public List<Evcs> getEvcsByNEMA1450() {
        return evcsRepository.findByNEMA1450True();
    }

    public List<Evcs> getEvcsByNEMA515() {
        return evcsRepository.findByNEMA515True();
    }

    public List<Evcs> getEvcsByNEMA520() {
        return evcsRepository.findByNEMA520True();
    }

    public List<Evcs> getEvcsByJ1772() {
        return evcsRepository.findByJ1772True();
    }

    public List<Evcs> getEvcsByJ1772COMBO() {
        return evcsRepository.findByJ1772COMBOTrue();
    }

    public List<Evcs> getEvcsByCHADEMO() {
        return evcsRepository.findByCHADEMOTrue();
    }

    public List<Evcs> getEvcsByTESLA() {
        return evcsRepository.findByTESLATrue();
    }

    public List<Evcs> getPublicEvcs() {
        return evcsRepository.findByIsPublicTrue();
    }

    public List<Evcs> getPrivateEvcs() {
        return evcsRepository.findByIsPublicFalse();
    }

    public List<Evcs> getEvcsByConnectorTypes(List<String> types) {
        Set<Evcs> evcsSet = new HashSet<>();
        if (types.contains(ConnectorType.NEMA1450.name()))
            evcsSet.addAll(evcsRepository.findByNEMA1450True());
        if (types.contains(ConnectorType.NEMA515.name()))
            evcsSet.addAll(evcsRepository.findByNEMA515True());
        if (types.contains(ConnectorType.NEMA520.name()))
            evcsSet.addAll(evcsRepository.findByNEMA520True());
        if (types.contains(ConnectorType.J1772.name()))
            evcsSet.addAll(evcsRepository.findByJ1772True());
        if (types.contains(ConnectorType.J1772COMBO.name()))
            evcsSet.addAll(evcsRepository.findByJ1772COMBOTrue());
        if (types.contains(ConnectorType.CHADEMO.name()))
            evcsSet.addAll(evcsRepository.findByCHADEMOTrue());
        if (types.contains(ConnectorType.TESLA.name()))
            evcsSet.addAll(evcsRepository.findByTESLATrue());
        return new ArrayList<>(evcsSet);
    }

    public List<Evcs> getEvcsByZipCodeAndConnectorTypes(String zip_code, List<String> types) {
        Set<Evcs> evcsSet = new HashSet<>();
        if (types.contains(ConnectorType.NEMA1450.name()))
            evcsSet.addAll(evcsRepository.findByZipCodeZipCodeAndNEMA1450True(zip_code));
        if (types.contains(ConnectorType.NEMA515.name()))
            evcsSet.addAll(evcsRepository.findByZipCodeZipCodeAndNEMA515True(zip_code));
        if (types.contains(ConnectorType.NEMA520.name()))
            evcsSet.addAll(evcsRepository.findByZipCodeZipCodeAndNEMA520True(zip_code));
        if (types.contains(ConnectorType.J1772.name()))
            evcsSet.addAll(evcsRepository.findByZipCodeZipCodeAndJ1772True(zip_code));
        if (types.contains(ConnectorType.J1772COMBO.name()))
            evcsSet.addAll(evcsRepository.findByZipCodeZipCodeAndJ1772COMBOTrue(zip_code));
        if (types.contains(ConnectorType.CHADEMO.name()))
            evcsSet.addAll(evcsRepository.findByZipCodeZipCodeAndCHADEMOTrue(zip_code));
        if (types.contains(ConnectorType.TESLA.name()))
            evcsSet.addAll(evcsRepository.findByZipCodeZipCodeAndTESLATrue(zip_code));
        return new ArrayList<>(evcsSet);
    }

    public List<Evcs> getEvcsByDcFastPoints(Integer points) {
        return evcsRepository.findByDcFastPointsGreaterThanEqual(points);
    }

    public List<Evcs> getEvcsByLevel1Points(Integer points) {
        return evcsRepository.findByLevel1PointsGreaterThanEqual(points);
    }

    public List<Evcs> getEvcsByLevel2Points(Integer points) {
        return evcsRepository.findByLevel2PointsGreaterThanEqual(points);
    }

    public List<Evcs> getEvcsByTotalPoints(Integer points) {
        return evcsRepository.findByTotalPointsGreaterThanEqual(points);
    }

    public List<Evcs> getEvcsByPoints(Map<String, String> pointsMap) {
        Set<Evcs> evcsSet = new HashSet<>();
        if (pointsMap.keySet().contains(Constants.LEVEL_1))
            evcsSet.addAll(
                    evcsRepository.findByLevel1PointsGreaterThanEqual(Integer.valueOf(pointsMap.getOrDefault(Constants.LEVEL_1, Constants.ZERO))));
        if (pointsMap.keySet().contains(Constants.LEVEL_2))
            evcsSet.addAll(
                    evcsRepository.findByLevel2PointsGreaterThanEqual(Integer.valueOf(pointsMap.getOrDefault(Constants.LEVEL_2, Constants.ZERO))));
        if (pointsMap.keySet().contains(Constants.DC_FAST))
            evcsSet.addAll(
                    evcsRepository.findByDcFastPointsGreaterThanEqual(Integer.valueOf(pointsMap.getOrDefault(Constants.DC_FAST, Constants.ZERO))));
        if (pointsMap.keySet().contains(Constants.TOTAL))
            evcsSet.addAll(
                    evcsRepository.findByTotalPointsGreaterThanEqual(Integer.valueOf(pointsMap.getOrDefault(Constants.TOTAL, Constants.ZERO))));
        return new ArrayList<>(evcsSet);
    }

    public List<Evcs> getEvcsByZipCodeAndPoints(String zip_code, Map<String, String> pointsMap) {
        Set<Evcs> evcsSet = new HashSet<>();
        if (pointsMap.keySet().contains(Constants.LEVEL_1))
            evcsSet.addAll(evcsRepository.findByZipCodeZipCodeAndLevel1PointsGreaterThanEqual(zip_code,
                    Integer.valueOf(pointsMap.getOrDefault(Constants.LEVEL_1, Constants.ZERO))));
        if (pointsMap.keySet().contains(Constants.LEVEL_2))
            evcsSet.addAll(evcsRepository.findByZipCodeZipCodeAndLevel2PointsGreaterThanEqual(zip_code,
                    Integer.valueOf(pointsMap.getOrDefault(Constants.LEVEL_2, Constants.ZERO))));
        if (pointsMap.keySet().contains(Constants.DC_FAST))
            evcsSet.addAll(evcsRepository.findByZipCodeZipCodeAndDcFastPointsGreaterThanEqual(zip_code,
                    Integer.valueOf(pointsMap.getOrDefault(Constants.DC_FAST, Constants.ZERO))));
        if (pointsMap.keySet().contains(Constants.TOTAL))
            evcsSet.addAll(evcsRepository.findByZipCodeZipCodeAndTotalPointsGreaterThanEqual(zip_code,
                    Integer.valueOf(pointsMap.getOrDefault(Constants.TOTAL, Constants.ZERO))));
        return new ArrayList<>(evcsSet);
    }

    public Evcs updateEvcs(Long id, Evcs evcs) {
        evcs.setTotalPoints(evcs.getDcFastPoints() + evcs.getLevel1Points() + evcs.getLevel2Points());
        return evcsRepository.save(evcs);
    }

    public void deleteEvcsByZipCode(String zip_code) {
        evcsRepository.deleteByZipCodeZipCode(zip_code);
    }

    public void deleteEvcsByName(String name) {
        evcsRepository.deleteByName(name);
    }

    public void deleteEvcsById(Long id) {
        evcsRepository.deleteById(id);
    }
}
