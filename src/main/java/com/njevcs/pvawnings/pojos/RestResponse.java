/**
 * 
 */
package com.njevcs.pvawnings.pojos;

import java.io.Serializable;

/**
 * @author patel
 *
 *         Nov 23, 2024
 */
public class RestResponse<T> implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -969499960425577870L;
    private T data;
    private int total;
    private String errorMessage;

    /**
     * 
     */
    public RestResponse() {
        super();
    }

    /**
     * @param data
     * @param total
     * @param errorMessage
     */
    public RestResponse(T data, int total, String errorMessage) {
        this.data = data;
        this.total = total;
        this.errorMessage = errorMessage;
    }

    /**
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


}
