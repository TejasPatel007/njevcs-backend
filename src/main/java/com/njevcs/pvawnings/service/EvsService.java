/**
 * 
 */
package com.njevcs.pvawnings.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import com.njevcs.pvawnings.entity.Evs;
import com.njevcs.pvawnings.repository.EvsRepository;
import com.njevcs.pvawnings.utils.Constants;
import com.njevcs.pvawnings.utils.Utility;

/**
 * @author patel
 *
 *         Nov 14, 2024
 */
@Service
public class EvsService {

    @Autowired
    private EvsRepository evsRepository;

    @Caching(
            evict = {@CacheEvict(value = "evsList", allEntries = true, beforeInvocation = true),
                    @CacheEvict(value = "evsCity", allEntries = true, beforeInvocation = true),
                    @CacheEvict(value = "evsCities", allEntries = true, beforeInvocation = true),
                    @CacheEvict(value = "evsCounty", allEntries = true, beforeInvocation = true),
                    @CacheEvict(value = "evsCounties", allEntries = true, beforeInvocation = true)},
            put = {@CachePut(value = "evs", key = "#zip_code")})
    public Evs createEvs(String zip_code, Evs evs) {
        return evsRepository.save(evs);
    }

    @Cacheable(value = "evsList")
    public List<Map<String, Object>> getAllEvs() {
        return evsRepository.findAll().stream().map(evs -> {
            Map<String, Object> map = new HashMap<>();
            map.put(Constants.ZIP_CODE, evs.getZipCode());
            map.put(Constants.NUMBER_OF_EVS, evs.getNumberOfEvs());
            map.put(Constants.EVS_LEVEL, Utility.getEvsLevel(evs.getNumberOfEvs()));
            return map;
        }).collect(Collectors.toList());
    }

    @Cacheable(value = "evs", key = "#zip_code")
    public Map<String, Object> getEvsByZip_Code(String zip_code) {
        Evs evsByZipCode = evsRepository.findByZipCode(zip_code);
        Map<String, Object> map = new HashMap<>();
        map.put(Constants.ZIP_CODE, evsByZipCode.getZipCode());
        map.put(Constants.NUMBER_OF_EVS, evsByZipCode.getNumberOfEvs());
        map.put(Constants.EVS_LEVEL, Utility.getEvsLevel(evsByZipCode.getNumberOfEvs()));
        return map;
    }

    @Cacheable(value = "evsCity", key = "#city")
    public Map<String, Object> getEvsByCity(String city) {
        Map<String, Object> evsByCity = new HashMap<>(evsRepository.findByCity(city));
        Integer numberOfEvs = ((BigInteger) evsByCity.getOrDefault(Constants.NUMBER_OF_EVS, BigInteger.ZERO)).intValue();
        if (numberOfEvs.intValue() == 0) {
            evsByCity.put(Constants.CITY, city);
        }
        evsByCity.put(Constants.NUMBER_OF_EVS, numberOfEvs);
        evsByCity.put(Constants.EVS_LEVEL, Utility.getCityEvsLevel(numberOfEvs));
        return evsByCity;
    }

    @Cacheable(value = "evsCities")
    public List<Map<String, Object>> getEvsForAllCities() {
        return evsRepository.findByAllCities().stream().map(row -> {
            Map<String, Object> map = new HashMap<>();
            map.put(Constants.CITY, row.getOrDefault(Constants.CITY, ""));
            Integer numberOfEvs = ((BigInteger) row.getOrDefault(Constants.NUMBER_OF_EVS, BigInteger.ZERO)).intValue();
            map.put(Constants.NUMBER_OF_EVS, numberOfEvs);
            map.put(Constants.EVS_LEVEL, Utility.getCityEvsLevel(numberOfEvs));
            return map;
        }).collect(Collectors.toList());
    }

    @Cacheable(value = "evsCounty", key = "#county")
    public Map<String, Object> getEvsByCounty(String county) {
        Map<String, Object> evsByCounty = new HashMap<>(evsRepository.findByCounty(county));
        Integer numberOfEvs = ((BigInteger) evsByCounty.getOrDefault(Constants.NUMBER_OF_EVS, BigInteger.ZERO)).intValue();
        if (numberOfEvs.intValue() == 0) {
            evsByCounty.put(Constants.COUNTY, county);
        }
        evsByCounty.put(Constants.NUMBER_OF_EVS, numberOfEvs);
        evsByCounty.put(Constants.EVS_LEVEL, Utility.getCountyEvsLevel(numberOfEvs));
        return evsByCounty;
    }

    @Cacheable(value = "evsCounties")
    public List<Map<String, Object>> getEvsForAllCounties() {
        return evsRepository.findByAllCounties().stream().map(row -> {
            Map<String, Object> map = new HashMap<>();
            map.put(Constants.COUNTY, row.getOrDefault(Constants.COUNTY, ""));
            Integer numberOfEvs = ((BigInteger) row.getOrDefault(Constants.NUMBER_OF_EVS, BigInteger.ZERO)).intValue();
            map.put(Constants.NUMBER_OF_EVS, numberOfEvs);
            map.put(Constants.EVS_LEVEL, Utility.getCountyEvsLevel(numberOfEvs));
            return map;
        }).collect(Collectors.toList());
    }

    @Caching(
            evict = {@CacheEvict(value = "evsList", allEntries = true, beforeInvocation = true),
                    @CacheEvict(value = "evsCity", allEntries = true, beforeInvocation = true),
                    @CacheEvict(value = "evsCities", allEntries = true, beforeInvocation = true),
                    @CacheEvict(value = "evsCounty", allEntries = true, beforeInvocation = true),
                    @CacheEvict(value = "evsCounties", allEntries = true, beforeInvocation = true)},
            put = {@CachePut(value = "evs", key = "#zip_code")})
    public Evs updateEvs(String zip_code, Evs evs) {
        return evsRepository.save(evs);
    }

    @Caching(evict = {@CacheEvict(value = "evsList", allEntries = true, beforeInvocation = true),
            @CacheEvict(value = "evsCity", allEntries = true, beforeInvocation = true),
            @CacheEvict(value = "evsCities", allEntries = true, beforeInvocation = true),
            @CacheEvict(value = "evsCounty", allEntries = true, beforeInvocation = true),
            @CacheEvict(value = "evsCounties", allEntries = true, beforeInvocation = true), @CacheEvict(value = "evs", key = "#zip_code")})
    public void deleteEvs(String zip_code) {
        evsRepository.deleteByZipCode(zip_code);
    }
}
