/**
 * 
 */
package com.njevcs.pvawnings.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.njevcs.pvawnings.entity.Stores;
import com.njevcs.pvawnings.entity.ZipCodes;
import com.njevcs.pvawnings.repository.StoresRepository;

/**
 * @author patel
 *
 *         Nov 14, 2024
 */
@Service
public class StoresService {

    @Autowired
    private StoresRepository storesRepository;

    @Autowired
    private ZipCodesService zipCodesService;

    public Stores createStores(Stores stores) {
        return storesRepository.save(stores);
    }

    public List<Stores> getAllStores() {
        return storesRepository.findAll();
    }

    public Stores getStoresById(Long id) {
        return storesRepository.findById(id).orElse(null);
    }

    public List<Stores> getStoresByZip_Code(String zip_code) {
        return storesRepository.findByZipCodeZipCode(zip_code);
    }

    @Cacheable(value = "storesCity", key = "#city")
    public List<Stores> getStoresByCity(String city) {
        List<String> zipCodes = zipCodesService.getByCity(city).stream().map(ZipCodes::getZipCode).collect(Collectors.toList());
        return storesRepository.findByZipCodeZipCodeIn(zipCodes);
    }

    @Cacheable(value = "storesCounty", key = "#county")
    public List<Stores> getStoresByCounty(String county) {
        List<String> zipCodes = zipCodesService.getByCounty(county).stream().map(ZipCodes::getZipCode).collect(Collectors.toList());
        return storesRepository.findByZipCodeZipCodeIn(zipCodes);
    }

    public List<Stores> getStoresByName(String name) {
        return storesRepository.findByName(name);
    }

    public List<Stores> getStoresByZipCodeAndName(String zip_code, String name) {
        return storesRepository.findByZipCodeZipCodeAndName(zip_code, name);
    }

    public List<Stores> getStoresByZipCodeAndAddress(String zip_code, String address) {
        return storesRepository.findByZipCodeZipCodeAndAddress(zip_code, address);
    }

    public List<Stores> getStoresByParkingAreaGreaterThanEqual(Long parkingArea) {
        return storesRepository.findByParkingAreaGreaterThanEqual(parkingArea);
    }

    public List<Stores> getStoresByParkingAreaBetween(Long minParkingArea, Long maxParkingArea) {
        return storesRepository.findByParkingAreaBetween(minParkingArea, maxParkingArea);
    }

    public List<Stores> getStoresByZipCodeAndParkingAreaGreaterThanEqual(String zip_code, Long parkingArea) {
        return storesRepository.findByZipCodeZipCodeAndParkingAreaGreaterThanEqual(zip_code, parkingArea);
    }

    public List<Stores> getStoresByZipCodeAndParkingAreaBetween(String zip_code, Long minParkingArea, Long maxParkingArea) {
        return storesRepository.findByZipCodeZipCodeAndParkingAreaBetween(zip_code, minParkingArea, maxParkingArea);
    }

    public Stores updateStores(Long id, Stores stores) {
        return storesRepository.save(stores);
    }

    public void deleteStoresByZipCode(String zip_code) {
        storesRepository.deleteByZipCodeZipCode(zip_code);
    }

    public void deleteStoresByName(String name) {
        storesRepository.deleteByName(name);
    }

    public void deleteStoresById(Long id) {
        storesRepository.deleteById(id);
    }
}
