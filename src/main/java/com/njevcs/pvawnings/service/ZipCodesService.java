/**
 * 
 */
package com.njevcs.pvawnings.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import com.njevcs.pvawnings.entity.ZipCodes;
import com.njevcs.pvawnings.repository.ZipCodesRepository;

/**
 * @author patel
 *
 *         Nov 14, 2024
 */
@Service
public class ZipCodesService {

    @Autowired
    private ZipCodesRepository zipCodesRepository;

    @Caching(
            evict = {@CacheEvict(value = "zipcodeList", allEntries = true, beforeInvocation = true),
                    @CacheEvict(value = "zipcodeCity", allEntries = true, beforeInvocation = true),
                    @CacheEvict(value = "zipcodeCityList", allEntries = true, beforeInvocation = true),
                    @CacheEvict(value = "zipcodeCounty", allEntries = true, beforeInvocation = true)},
            put = {@CachePut(value = "zipcode", key = "#zip_code")})
    public ZipCodes createZipCodes(String zip_code, ZipCodes ZipCodes) {
        return zipCodesRepository.save(ZipCodes);
    }

    @Cacheable(value = "zipcodeList")
    public List<ZipCodes> getAllZipCodes() {
        return zipCodesRepository.findAll();
    }

    @Cacheable(value = "zipcode", key = "#zip_code")
    public ZipCodes getByZip_Code(String zip_code) {
        return zipCodesRepository.findByZipCode(zip_code);
    }

    @Cacheable(value = "zipcodeCity", key = "#city")
    public List<ZipCodes> getByCity(String city) {
        return zipCodesRepository.findByCity(city);
    }

    public Set<String> getByCityStartingWith(String city) {
        return zipCodesRepository.findDistinctCityByCityStartingWithOrderByCityAsc(city).stream().map(ZipCodes::getCity).collect(Collectors.toSet());
    }

    @Cacheable(value = "zipcodeCityList")
    public List<ZipCodes> getAllCities() {

        List<Map<String, Object>> results = zipCodesRepository.findAllCities();
        List<ZipCodes> allCities = new ArrayList<>();

        for (Map<String, Object> result : results) {
            ZipCodes zipCodes = new ZipCodes();
            zipCodes.setCity((String) result.get("city"));
            zipCodes.setCounty((String) result.get("county"));
            zipCodes.setZipCode((String) result.get("zip_code"));
            allCities.add(zipCodes);
        }

        return allCities;
    }

    @Cacheable(value = "zipcodeCounty", key = "#county")
    public List<ZipCodes> getByCounty(String county) {
        return zipCodesRepository.findByCounty(county);
    }

    public Set<String> getByCountyStartingWith(String county) {
        return zipCodesRepository.findDistinctCountyByCountyStartingWithOrderByCountyAsc(county).stream().map(ZipCodes::getCounty)
                .collect(Collectors.toSet());
    }

    @Caching(
            evict = {@CacheEvict(value = "zipcodeList", allEntries = true, beforeInvocation = true),
                    @CacheEvict(value = "zipcodeCity", allEntries = true, beforeInvocation = true),
                    @CacheEvict(value = "zipcodeCityList", allEntries = true, beforeInvocation = true),
                    @CacheEvict(value = "zipcodeCounty", allEntries = true, beforeInvocation = true)},
            put = {@CachePut(value = "zipcode", key = "#zip_code")})
    public ZipCodes updateZipCodes(String zip_code, ZipCodes zipCodes) {
        return zipCodesRepository.save(zipCodes);
    }

    @Caching(evict = {@CacheEvict(value = "zipcodeList", allEntries = true, beforeInvocation = true),
            @CacheEvict(value = "zipcodeCity", allEntries = true, beforeInvocation = true),
            @CacheEvict(value = "zipcodeCityList", allEntries = true, beforeInvocation = true),
            @CacheEvict(value = "zipcodeCounty", allEntries = true, beforeInvocation = true), @CacheEvict(value = "zipcode", key = "#zip_code")})
    public void deleteZipCodes(String zip_code) {
        zipCodesRepository.deleteByZipCode(zip_code);
    }

    @Caching(evict = {@CacheEvict(value = "zipcodeList", allEntries = true, beforeInvocation = true),
            @CacheEvict(value = "zipcodeCity", allEntries = true, beforeInvocation = true),
            @CacheEvict(value = "zipcodeCityList", allEntries = true, beforeInvocation = true),
            @CacheEvict(value = "zipcodeCounty", allEntries = true, beforeInvocation = true)})
    public void deleteCity(String city) {
        zipCodesRepository.deleteByCity(city);
    }

    @Caching(evict = {@CacheEvict(value = "zipcodeList", allEntries = true, beforeInvocation = true),
            @CacheEvict(value = "zipcodeCity", allEntries = true, beforeInvocation = true),
            @CacheEvict(value = "zipcodeCityList", allEntries = true, beforeInvocation = true),
            @CacheEvict(value = "zipcodeCounty", allEntries = true, beforeInvocation = true)})
    public void deleteCounty(String county) {
        zipCodesRepository.deleteByCounty(county);
    }
}
