package com.njevcs.pvawnings.pojos;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 
 * @author patel
 *
 *         Nov 19, 2024
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetadataSources {

    private List<String> sources;

    /**
     * @return the sources
     */
    public List<String> getSources() {
        return sources;
    }

    /**
     * @param sources the sources to set
     */
    public void setSources(List<String> sources) {
        this.sources = sources;
    }

}
