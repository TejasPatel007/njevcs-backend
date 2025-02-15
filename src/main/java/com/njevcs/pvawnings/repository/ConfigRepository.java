/**
 * 
 */
package com.njevcs.pvawnings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.njevcs.pvawnings.entity.Config;

/**
 * 
 * @author patel
 *
 *         Feb 15, 2025
 */
@Repository
@Transactional
public interface ConfigRepository extends JpaRepository<Config, String> {

    Config findByName(String name);

}
