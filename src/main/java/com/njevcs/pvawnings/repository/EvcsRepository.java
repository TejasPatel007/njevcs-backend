/**
 * 
 */
package com.njevcs.pvawnings.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.njevcs.pvawnings.entity.Evcs;

/**
 * @author patel
 *
 *         Nov 14, 2024
 */
@Repository
@Transactional
public interface EvcsRepository extends JpaRepository<Evcs, Long> {

    List<Evcs> findByZipCodeZipCode(String zip_code);

    List<Evcs> findByZipCodeZipCodeIn(List<String> zip_code);

    void deleteByZipCodeZipCode(String zip_code);

    List<Evcs> findByName(String name);

    void deleteByName(String name);

    List<Evcs> findByZipCodeZipCodeAndName(String zip_code, String name);

    List<Evcs> findByZipCodeZipCodeAndAddress(String zip_code, String address);

    List<Evcs> findByNEMA1450True();

    List<Evcs> findByNEMA515True();

    List<Evcs> findByNEMA520True();

    List<Evcs> findByJ1772True();

    List<Evcs> findByJ1772COMBOTrue();

    List<Evcs> findByCHADEMOTrue();

    List<Evcs> findByTESLATrue();

    List<Evcs> findByIsPublicTrue();

    List<Evcs> findByIsPublicFalse();

    List<Evcs> findByZipCodeZipCodeAndNEMA1450True(String zip_code);

    List<Evcs> findByZipCodeZipCodeAndNEMA515True(String zip_code);

    List<Evcs> findByZipCodeZipCodeAndNEMA520True(String zip_code);

    List<Evcs> findByZipCodeZipCodeAndJ1772True(String zip_code);

    List<Evcs> findByZipCodeZipCodeAndJ1772COMBOTrue(String zip_code);

    List<Evcs> findByZipCodeZipCodeAndCHADEMOTrue(String zip_code);

    List<Evcs> findByZipCodeZipCodeAndTESLATrue(String zip_code);

    List<Evcs> findByDcFastPointsGreaterThanEqual(Integer points);

    List<Evcs> findByLevel1PointsGreaterThanEqual(Integer points);

    List<Evcs> findByLevel2PointsGreaterThanEqual(Integer points);

    List<Evcs> findByTotalPointsGreaterThanEqual(Integer points);

    List<Evcs> findByZipCodeZipCodeAndDcFastPointsGreaterThanEqual(String zip_code, Integer points);

    List<Evcs> findByZipCodeZipCodeAndLevel1PointsGreaterThanEqual(String zip_code, Integer points);

    List<Evcs> findByZipCodeZipCodeAndLevel2PointsGreaterThanEqual(String zip_code, Integer points);

    List<Evcs> findByZipCodeZipCodeAndTotalPointsGreaterThanEqual(String zip_code, Integer points);

}
