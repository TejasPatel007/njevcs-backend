/**
 * 
 */
package com.njevcs.pvawnings.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.njevcs.pvawnings.entity.Stores;

/**
 * @author patel
 *
 *         Nov 14, 2024
 */
@Repository
@Transactional
public interface StoresRepository extends JpaRepository<Stores, Long> {

    List<Stores> findByZipCodeZipCode(String zip_code);

    List<Stores> findByZipCodeZipCodeIn(List<String> zip_code);

    void deleteByZipCodeZipCode(String zip_code);

    List<Stores> findByName(String name);

    void deleteByName(String name);

    List<Stores> findByZipCodeZipCodeAndName(String zip_code, String name);

    List<Stores> findByZipCodeZipCodeAndAddress(String zip_code, String address);

    List<Stores> findByParkingAreaGreaterThanEqual(Long parkingArea);

    List<Stores> findByParkingAreaBetween(Long minParkingArea, Long maxParkingArea);

    List<Stores> findByZipCodeZipCodeAndParkingAreaGreaterThanEqual(String zip_code, Long parkingArea);

    List<Stores> findByZipCodeZipCodeAndParkingAreaBetween(String zip_code, Long minParkingArea, Long maxParkingArea);

}
