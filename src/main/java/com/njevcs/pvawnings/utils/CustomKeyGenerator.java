/**
 * 
 */
package com.njevcs.pvawnings.utils;

import java.lang.reflect.Method;
import org.springframework.cache.interceptor.KeyGenerator;

/**
 * 
 * @author patel
 *
 *         Nov 23, 2024
 */
public class CustomKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        // return target.getClass().getSimpleName() + "_" + method.getName() + "_" +
        // StringUtils.arrayToDelimitedString(params, "_");
        return method.getParameters()[0].getName() + "_" + params[0];
    }

}
