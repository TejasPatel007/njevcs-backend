/**
 * 
 */
package com.njevcs.pvawnings.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.njevcs.pvawnings.pojos.RestResponse;

/**
 * @author patel
 *
 *         Nov 23, 2024
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestResponse<Object>> handleException(Exception ex) {

        // Log the root cause
        Throwable rootCause = getRootCause(ex);
        LOGGER.error(rootCause.getMessage(), rootCause);

        // Return an appropriate response
        RestResponse<Object> response = new RestResponse<>();
        response.setErrorMessage(ThreadContext.containsKey("errorMessage") ? ThreadContext.get("errorMessage") : rootCause.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Utility method to find the root cause of an exception.
     */
    private Throwable getRootCause(Throwable throwable) {
        Throwable rootCause = throwable;
        while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
            rootCause = rootCause.getCause();
        }
        return rootCause;
    }
}
