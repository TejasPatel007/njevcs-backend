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
import com.njevcs.pvawnings.entity.Economy;
import com.njevcs.pvawnings.repository.EconomyRepository;
import com.njevcs.pvawnings.utils.Constants;
import com.njevcs.pvawnings.utils.Utility;

/**
 * @author patel
 *
 *         Nov 14, 2024
 */
@Service
public class EconomyService {

    @Autowired
    private EconomyRepository economyRepository;

    @Caching(
            evict = {@CacheEvict(value = "economyList", allEntries = true, beforeInvocation = true),
                    @CacheEvict(value = "economyCity", allEntries = true, beforeInvocation = true),
                    @CacheEvict(value = "economyCities", allEntries = true, beforeInvocation = true),
                    @CacheEvict(value = "economyCounty", allEntries = true, beforeInvocation = true),
                    @CacheEvict(value = "economyCounties", allEntries = true, beforeInvocation = true)},
            put = {@CachePut(value = "economy", key = "#zip_code")})
    public Economy createEconomy(String zip_code, Economy economy) {
        return economyRepository.save(economy);
    }

    @Cacheable(value = "economyList")
    public List<Map<String, Object>> getAllEconomy() {
        return economyRepository.findAll().stream().map(economy -> {
            Map<String, Object> map = new HashMap<>();
            map.put(Constants.ZIP_CODE, economy.getZipCode());
            map.put(Constants.INCOME, economy.getIncome());
            map.put(Constants.INCOME_LEVEL, Utility.getIncomeLevel(economy.getIncome()));
            return map;
        }).collect(Collectors.toList());
    }

    @Cacheable(value = "economy", key = "#zip_code")
    public Map<String, Object> getEconomyByZip_Code(String zip_code) {
        Economy economyByZipCode = economyRepository.findByZipCode(zip_code);
        Map<String, Object> map = new HashMap<>();
        map.put(Constants.ZIP_CODE, economyByZipCode.getZipCode());
        map.put(Constants.INCOME, economyByZipCode.getIncome());
        map.put(Constants.INCOME_LEVEL, Utility.getIncomeLevel(economyByZipCode.getIncome()));
        return map;
    }

    @Cacheable(value = "economyCity", key = "#city")
    public Map<String, Object> getEconomyByCity(String city) {
        Map<String, Object> economyByCity = new HashMap<>(economyRepository.findByCity(city));
        Integer income = ((BigInteger) economyByCity.getOrDefault(Constants.INCOME, BigInteger.ZERO)).intValue();
        if (income.intValue() == 0) {
            economyByCity.put(Constants.CITY, city);
            economyByCity.put(Constants.INCOME, 0);
        }
        economyByCity.put(Constants.INCOME_LEVEL, Utility.getIncomeLevel(income));
        return economyByCity;
    }

    @Cacheable(value = "economyCities")
    public List<Map<String, Object>> getEconomyForAllCities() {
        return economyRepository.findByAllCities().stream().map(row -> {
            Map<String, Object> map = new HashMap<>();
            map.put(Constants.CITY, row.getOrDefault(Constants.CITY, ""));
            Integer income = ((BigInteger) row.getOrDefault(Constants.INCOME, BigInteger.ZERO)).intValue();
            map.put(Constants.INCOME, income);
            map.put(Constants.INCOME_LEVEL, Utility.getIncomeLevel(income));
            return map;
        }).collect(Collectors.toList());
    }

    @Cacheable(value = "economyCounty", key = "#county")
    public Map<String, Object> getEconomyByCounty(String county) {
        Map<String, Object> economyByCounty = new HashMap<>(economyRepository.findByCounty(county));
        Integer income = ((BigInteger) economyByCounty.getOrDefault(Constants.INCOME, BigInteger.ZERO)).intValue();
        if (income.intValue() == 0) {
            economyByCounty.put(Constants.COUNTY, county);
            economyByCounty.put(Constants.INCOME, 0);
        }
        economyByCounty.put(Constants.INCOME_LEVEL, Utility.getIncomeLevel(income));
        return economyByCounty;
    }

    @Cacheable(value = "economyCounties")
    public List<Map<String, Object>> getEconomyForAllCounties() {
        return economyRepository.findByAllCounties().stream().map(row -> {
            Map<String, Object> map = new HashMap<>();
            map.put(Constants.COUNTY, row.getOrDefault(Constants.COUNTY, ""));
            Integer income = ((BigInteger) row.getOrDefault(Constants.INCOME, BigInteger.ZERO)).intValue();
            map.put(Constants.INCOME, income);
            map.put(Constants.INCOME_LEVEL, Utility.getIncomeLevel(income));
            return map;
        }).collect(Collectors.toList());
    }

    @Caching(
            evict = {@CacheEvict(value = "economyList", allEntries = true, beforeInvocation = true),
                    @CacheEvict(value = "economyCity", allEntries = true, beforeInvocation = true),
                    @CacheEvict(value = "economyCities", allEntries = true, beforeInvocation = true),
                    @CacheEvict(value = "economyCounty", allEntries = true, beforeInvocation = true),
                    @CacheEvict(value = "economyCounties", allEntries = true, beforeInvocation = true)},
            put = {@CachePut(value = "economy", key = "#zip_code")})
    public Economy updateEconomy(String zip_code, Economy economy) {
        return economyRepository.save(economy);
    }

    @Caching(evict = {@CacheEvict(value = "economyList", allEntries = true, beforeInvocation = true),
            @CacheEvict(value = "economyCity", allEntries = true, beforeInvocation = true),
            @CacheEvict(value = "economyCities", allEntries = true, beforeInvocation = true),
            @CacheEvict(value = "economyCounty", allEntries = true, beforeInvocation = true),
            @CacheEvict(value = "economyCounties", allEntries = true, beforeInvocation = true), @CacheEvict(value = "economy", key = "#zip_code")})
    public void deleteEconomy(String zip_code) {
        economyRepository.deleteByZipCode(zip_code);
    }
}
