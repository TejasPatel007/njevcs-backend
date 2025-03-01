/**
 * 
 */
package com.njevcs.pvawnings.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.njevcs.pvawnings.entity.Evs;
import com.njevcs.pvawnings.pojos.DemandDTO;

/**
 * @author patel
 *
 *         Nov 14, 2024
 */
@Repository
@Transactional
public interface DemandRepository extends JpaRepository<Evs, String> {

    @Query(value = "SELECT DISTINCT z.city, group_concat(distinct z.county) as county, IFNULL(CAST(e.numberOfEvs AS SIGNED), 0) AS totalEvs, IFNULL(CAST(eco.income AS SIGNED), 0) AS avgIncome, "
            + "CAST(evc.public_level1 AS SIGNED) AS publicLevel1, CAST(evc.public_level2 AS SIGNED) AS publicLevel2, "
            + "CAST(evc.public_dc_fast AS SIGNED) AS publicDcFast, CAST(evc.private_level1 AS SIGNED) AS privateLevel1, "
            + "CAST(evc.private_level2 AS SIGNED) AS privateLevel2, CAST(evc.private_dc_fast AS SIGNED) AS privateDcFast, "
            + "CAST(s.potential AS SIGNED) AS totalSolarKwhPerDay FROM zip_codes z LEFT JOIN "
            + "(SELECT city, IFNULL(ROUND(SUM(evs)), 0) AS numberOfEvs FROM evs e JOIN zip_codes z ON z.zip_code = e.zip_code GROUP BY z.city) e "
            + "ON z.city = e.city LEFT JOIN "
            + "(SELECT city, IFNULL(ROUND(AVG(income)), 0) AS income FROM economy e JOIN zip_codes z ON z.zip_code = e.zip_code GROUP BY z.city) eco "
            + "ON z.city = eco.city LEFT JOIN "
            + "(SELECT z.city, SUM(CASE WHEN evc.ispublic = 1 THEN evc.level1_points ELSE 0 END) AS public_level1, "
            + "SUM(CASE WHEN evc.ispublic = 1 THEN evc.level2_points ELSE 0 END) AS public_level2, "
            + "SUM(CASE WHEN evc.ispublic = 1 THEN evc.dc_fast_points ELSE 0 END) AS public_dc_fast, "
            + "SUM(CASE WHEN evc.ispublic = 0 THEN evc.level1_points ELSE 0 END) AS private_level1, "
            + "SUM(CASE WHEN evc.ispublic = 0 THEN evc.level2_points ELSE 0 END) AS private_level2, "
            + "SUM(CASE WHEN evc.ispublic = 0 THEN evc.dc_fast_points ELSE 0 END) AS private_dc_fast FROM zip_codes z LEFT JOIN "
            + "evcs evc ON z.zip_code = evc.zip_code GROUP BY z.city) evc ON z.city = evc.city LEFT JOIN "
            + "(SELECT z.city, IFNULL(ROUND(SUM(st.parking_area * sg.annual * 0.216 * 0.83 * 0.7)), 0) AS potential FROM zip_codes z LEFT JOIN "
            + "stores st JOIN stores_ghi sg ON st.id = sg.stores_id ON st.zip_code = z.zip_code GROUP BY z.city) s ON s.city = z.city "
            + "WHERE z.city = ?1 GROUP BY z.city", nativeQuery = true)
    DemandDTO getDetailsForCity(String city);

    @Query(value = "SELECT DISTINCT z.city, group_concat(distinct z.county) as county, IFNULL(CAST(e.numberOfEvs AS SIGNED), 0) AS totalEvs, IFNULL(CAST(eco.income AS SIGNED), 0) AS avgIncome, "
            + "CAST(evc.public_level1 AS SIGNED) AS publicLevel1, CAST(evc.public_level2 AS SIGNED) AS publicLevel2, "
            + "CAST(evc.public_dc_fast AS SIGNED) AS publicDcFast, CAST(evc.private_level1 AS SIGNED) AS privateLevel1, "
            + "CAST(evc.private_level2 AS SIGNED) AS privateLevel2, CAST(evc.private_dc_fast AS SIGNED) AS privateDcFast, "
            + "CAST(s.potential AS SIGNED) AS totalSolarKwhPerDay FROM zip_codes z LEFT JOIN "
            + "(SELECT city, IFNULL(ROUND(SUM(evs)), 0) AS numberOfEvs FROM evs e JOIN zip_codes z ON z.zip_code = e.zip_code GROUP BY z.city) e "
            + "ON z.city = e.city LEFT JOIN "
            + "(SELECT city, IFNULL(ROUND(AVG(income)), 0) AS income FROM economy e JOIN zip_codes z ON z.zip_code = e.zip_code GROUP BY z.city) eco "
            + "ON z.city = eco.city LEFT JOIN "
            + "(SELECT z.city, SUM(CASE WHEN evc.ispublic = 1 THEN evc.level1_points ELSE 0 END) AS public_level1, "
            + "SUM(CASE WHEN evc.ispublic = 1 THEN evc.level2_points ELSE 0 END) AS public_level2, "
            + "SUM(CASE WHEN evc.ispublic = 1 THEN evc.dc_fast_points ELSE 0 END) AS public_dc_fast, "
            + "SUM(CASE WHEN evc.ispublic = 0 THEN evc.level1_points ELSE 0 END) AS private_level1, "
            + "SUM(CASE WHEN evc.ispublic = 0 THEN evc.level2_points ELSE 0 END) AS private_level2, "
            + "SUM(CASE WHEN evc.ispublic = 0 THEN evc.dc_fast_points ELSE 0 END) AS private_dc_fast FROM zip_codes z LEFT JOIN "
            + "evcs evc ON z.zip_code = evc.zip_code GROUP BY z.city) evc ON z.city = evc.city LEFT JOIN "
            + "(SELECT z.city, IFNULL(ROUND(SUM(st.parking_area * sg.annual * 0.216 * 0.83 * 0.7)), 0) AS potential FROM zip_codes z LEFT JOIN "
            + "stores st JOIN stores_ghi sg ON st.id = sg.stores_id ON st.zip_code = z.zip_code GROUP BY z.city) s ON s.city = z.city "
            + "GROUP BY z.city", nativeQuery = true)
    List<DemandDTO> getDetailsForAllCities();

    @Query(value = "SELECT DISTINCT '' as city, z.county, IFNULL(CAST(e.numberOfEvs AS SIGNED), 0) AS totalEvs, IFNULL(CAST(eco.income AS SIGNED), 0) AS avgIncome, "
            + "CAST(evc.public_level1 AS SIGNED) AS publicLevel1, CAST(evc.public_level2 AS SIGNED) AS publicLevel2, "
            + "CAST(evc.public_dc_fast AS SIGNED) AS publicDcFast, CAST(evc.private_level1 AS SIGNED) AS privateLevel1, "
            + "CAST(evc.private_level2 AS SIGNED) AS privateLevel2, CAST(evc.private_dc_fast AS SIGNED) AS privateDcFast, "
            + "CAST(s.potential AS SIGNED) AS totalSolarKwhPerDay FROM zip_codes z LEFT JOIN "
            + "(SELECT county, IFNULL(ROUND(SUM(evs)), 0) AS numberOfEvs FROM evs e JOIN zip_codes z ON z.zip_code = e.zip_code GROUP BY z.county) e "
            + "ON z.county = e.county LEFT JOIN "
            + "(SELECT county, IFNULL(ROUND(AVG(income)), 0) AS income FROM economy e JOIN zip_codes z ON z.zip_code = e.zip_code GROUP BY z.county) eco "
            + "ON z.county = eco.county LEFT JOIN "
            + "(SELECT z.county, SUM(CASE WHEN evc.ispublic = 1 THEN evc.level1_points ELSE 0 END) AS public_level1, "
            + "SUM(CASE WHEN evc.ispublic = 1 THEN evc.level2_points ELSE 0 END) AS public_level2, "
            + "SUM(CASE WHEN evc.ispublic = 1 THEN evc.dc_fast_points ELSE 0 END) AS public_dc_fast, "
            + "SUM(CASE WHEN evc.ispublic = 0 THEN evc.level1_points ELSE 0 END) AS private_level1, "
            + "SUM(CASE WHEN evc.ispublic = 0 THEN evc.level2_points ELSE 0 END) AS private_level2, "
            + "SUM(CASE WHEN evc.ispublic = 0 THEN evc.dc_fast_points ELSE 0 END) AS private_dc_fast FROM zip_codes z LEFT JOIN "
            + "evcs evc ON z.zip_code = evc.zip_code GROUP BY z.county) evc ON z.county = evc.county LEFT JOIN "
            + "(SELECT z.county, IFNULL(ROUND(SUM(st.parking_area * sg.annual * 0.216 * 0.83 * 0.7)), 0) AS potential FROM zip_codes z LEFT JOIN "
            + "stores st JOIN stores_ghi sg ON st.id = sg.stores_id ON st.zip_code = z.zip_code GROUP BY z.county) s ON s.county = z.county "
            + "WHERE z.county = ?1 GROUP BY z.county", nativeQuery = true)
    DemandDTO getDetailsForCounty(String county);

    @Query(value = "SELECT DISTINCT '' as city, z.county, IFNULL(CAST(e.numberOfEvs AS SIGNED), 0) AS totalEvs, IFNULL(CAST(eco.income AS SIGNED), 0) AS avgIncome, "
            + "CAST(evc.public_level1 AS SIGNED) AS publicLevel1, CAST(evc.public_level2 AS SIGNED) AS publicLevel2, "
            + "CAST(evc.public_dc_fast AS SIGNED) AS publicDcFast, CAST(evc.private_level1 AS SIGNED) AS privateLevel1, "
            + "CAST(evc.private_level2 AS SIGNED) AS privateLevel2, CAST(evc.private_dc_fast AS SIGNED) AS privateDcFast, "
            + "CAST(s.potential AS SIGNED) AS totalSolarKwhPerDay FROM zip_codes z LEFT JOIN "
            + "(SELECT county, IFNULL(ROUND(SUM(evs)), 0) AS numberOfEvs FROM evs e JOIN zip_codes z ON z.zip_code = e.zip_code GROUP BY z.county) e "
            + "ON z.county = e.county LEFT JOIN "
            + "(SELECT county, IFNULL(ROUND(AVG(income)), 0) AS income FROM economy e JOIN zip_codes z ON z.zip_code = e.zip_code GROUP BY z.county) eco "
            + "ON z.county = eco.county LEFT JOIN "
            + "(SELECT z.county, SUM(CASE WHEN evc.ispublic = 1 THEN evc.level1_points ELSE 0 END) AS public_level1, "
            + "SUM(CASE WHEN evc.ispublic = 1 THEN evc.level2_points ELSE 0 END) AS public_level2, "
            + "SUM(CASE WHEN evc.ispublic = 1 THEN evc.dc_fast_points ELSE 0 END) AS public_dc_fast, "
            + "SUM(CASE WHEN evc.ispublic = 0 THEN evc.level1_points ELSE 0 END) AS private_level1, "
            + "SUM(CASE WHEN evc.ispublic = 0 THEN evc.level2_points ELSE 0 END) AS private_level2, "
            + "SUM(CASE WHEN evc.ispublic = 0 THEN evc.dc_fast_points ELSE 0 END) AS private_dc_fast FROM zip_codes z LEFT JOIN "
            + "evcs evc ON z.zip_code = evc.zip_code GROUP BY z.county) evc ON z.county = evc.county LEFT JOIN "
            + "(SELECT z.county, IFNULL(ROUND(SUM(st.parking_area * sg.annual * 0.216 * 0.83 * 0.7)), 0) AS potential FROM zip_codes z LEFT JOIN "
            + "stores st JOIN stores_ghi sg ON st.id = sg.stores_id ON st.zip_code = z.zip_code GROUP BY z.county) s ON s.county = z.county "
            + "GROUP BY z.county", nativeQuery = true)
    List<DemandDTO> getDetailsForAllCounties();

}
