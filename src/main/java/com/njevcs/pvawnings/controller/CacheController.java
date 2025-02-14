/**
 * 
 */
package com.njevcs.pvawnings.controller;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author patel
 *
 *         Feb 10, 2025
 */
@RestController
@RequestMapping("/api/cache")
public class CacheController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${spring.application.name}")
    private String appName;

    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCacheForCurrentApp() {
        try {
            Set<String> keys = redisTemplate.keys(appName + ":*");
            if (keys != null && !keys.isEmpty()) {
                redisTemplate.delete(keys);
                return ResponseEntity.ok("Cache cleared for application: " + appName);
            } else {
                return ResponseEntity.ok("No cache found for application: " + appName);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to clear cache: " + e.getMessage());
        }
    }
}
