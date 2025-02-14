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
import com.njevcs.pvawnings.entity.Evs;

/**
 * @author patel
 *
 *         Nov 14, 2024
 */
@Repository
@Transactional
public interface EvsRepository extends JpaRepository<Evs, String> {

    Evs findByZipCode(String zip_code);

    List<Evs> findByZipCodeIn(List<String> zip_code);

    @Query(value = "select city, CAST(ROUND(SUM(IFNULL(evs,0))) as signed) as numberOfEvs from evs e join zip_codes z "
            + "on z.zip_code = e.zip_code and z.city = ?1 group by z.city", nativeQuery = true)
    Map<String, Object> findByCity(String city);

    @Query(value = "select city, CAST(ROUND(SUM(IFNULL(evs,0))) as signed) as numberOfEvs from evs e join zip_codes z "
            + "on z.zip_code = e.zip_code group by z.city", nativeQuery = true)
    List<Map<String, Object>> findByAllCities();

    @Query(value = "select county, CAST(ROUND(SUM(IFNULL(evs,0))) as signed) as numberOfEvs from evs e join zip_codes z "
            + "on z.zip_code = e.zip_code and z.county = ?1 group by z.county", nativeQuery = true)
    Map<String, Object> findByCounty(String county);

    @Query(value = "select county, CAST(ROUND(SUM(IFNULL(evs,0))) as signed) as numberOfEvs from evs e join zip_codes z "
            + "on z.zip_code = e.zip_code group by z.county", nativeQuery = true)
    List<Map<String, Object>> findByAllCounties();

    void deleteByZipCode(String zip_code);

}
