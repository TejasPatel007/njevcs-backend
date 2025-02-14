/**
 * 
 */
package com.njevcs.pvawnings.repository;

import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.njevcs.pvawnings.entity.ZipCodes;

/**
 * @author patel
 *
 *         Nov 14, 2024
 */
@Repository
@Transactional
public interface ZipCodesRepository extends JpaRepository<ZipCodes, String> {

    ZipCodes findByZipCode(String zip_code);

    List<ZipCodes> findByCity(String city);

    List<ZipCodes> findDistinctCityByCityStartingWithOrderByCityAsc(String city);

    @Query(value = "select group_concat(zip_code ORDER BY zip_code) as zip_code, city, group_concat(distinct county) as county "
            + "from zip_codes group by city", nativeQuery = true)
    List<Map<String, Object>> findAllCities();

    List<ZipCodes> findByCounty(String county);

    List<ZipCodes> findDistinctCountyByCountyStartingWithOrderByCountyAsc(String city);

    void deleteByZipCode(String zip_code);

    void deleteByCity(String city);

    void deleteByCounty(String county);
}
