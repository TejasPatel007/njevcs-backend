/**
 * 
 */
package com.njevcs.pvawnings.pojos;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author patel
 *
 *         Nov 19, 2024
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SolarResourceResponse {

    private String version;

    private List<String> warnings;

    private List<String> errors;

    private MetadataSources metadata;

    private Inputs inputs;

    private Outputs outputs;

    private String errorMessage;

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the warnings
     */
    public List<String> getWarnings() {
        return warnings;
    }

    /**
     * @param warnings the warnings to set
     */
    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }

    /**
     * @return the errors
     */
    public List<String> getErrors() {
        return errors;
    }

    /**
     * @param errors the errors to set
     */
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    /**
     * @return the metadata
     */
    public MetadataSources getMetadata() {
        return metadata;
    }

    /**
     * @param metadata the metadata to set
     */
    public void setMetadata(MetadataSources metadata) {
        this.metadata = metadata;
    }

    /**
     * @return the inputs
     */
    public Inputs getInputs() {
        return inputs;
    }

    /**
     * @param inputs the inputs to set
     */
    public void setInputs(Inputs inputs) {
        this.inputs = inputs;
    }

    /**
     * @return the outputs
     */
    public Outputs getOutputs() {
        return outputs;
    }

    /**
     * @param outputs the outputs to set
     */
    public void setOutputs(Outputs outputs) {
        this.outputs = outputs;
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
