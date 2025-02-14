/**
 * 
 */
package com.njevcs.pvawnings.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.njevcs.pvawnings.entity.Stores;
import com.njevcs.pvawnings.entity.StoresGHI;

/**
 * 
 * @author patel
 *
 *         Dec 4, 2024
 */
@Repository
@Transactional
public interface StoresGHIRepository extends JpaRepository<StoresGHI, Long> {

    StoresGHI findByStoresId(Long id);

    @Query("SELECT s FROM Stores s LEFT JOIN s.storesGHI ghi WHERE ghi.storesId IS NULL")
    List<Stores> findStoresWithoutGHI();

    @Modifying
    @Query("DELETE FROM StoresGHI s")
    void deleteAllGHIs();

}
