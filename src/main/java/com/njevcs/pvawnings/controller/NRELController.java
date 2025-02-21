/**
 * 
 */
package com.njevcs.pvawnings.controller;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.njevcs.pvawnings.pojos.SolarResourceResponse;
import com.njevcs.pvawnings.service.NRELService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import liquibase.repackaged.org.apache.commons.lang3.StringUtils;

/**
 * @author patel
 *
 *         Nov 14, 2024
 */
@RestController
@RequestMapping("/nrel")
@Api(tags = "NREL Controller", description = "National Renewable Energy Laboratory Controller")
public class NRELController {

    @Autowired
    private NRELService nrelService;

    @GetMapping
    @Operation(summary = "Get Solar Flux Data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solar Flux information for a location",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = SolarResourceResponse.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<SolarResourceResponse> getSolarFlux(
            @Parameter(name = "lat", description = "Latitude", in = ParameterIn.QUERY, array = @ArraySchema(schema = @Schema(type = "string")),
                    example = "40.8645729") @RequestParam(required = false) String lat,
            @Parameter(name = "lon", description = "Longitude", in = ParameterIn.QUERY, array = @ArraySchema(schema = @Schema(type = "string")),
                    example = "-74.2012681") @RequestParam(required = false) String lon,
            @Parameter(name = "address", description = "Address", in = ParameterIn.QUERY, array = @ArraySchema(schema = @Schema(type = "string")),
                    example = "1 Normal Ave.,Montclair, NJ 07043") @RequestParam(required = false) String address) {

        SolarResourceResponse response = nrelService.getSolarFluxResource(lat, lon, address);
        if (!StringUtils.isBlank(response.getErrorMessage())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    @Operation(summary = "Insert/Update Solar Flux Data for Stores")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Solar Flux Data for Stores Inserted/Updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<HttpStatus> insertUpdateStoresGHI(
            @Parameter(description = "A boolean parameter to delete all GHI stored for stores", required = false,
                    example = "true") @RequestParam(name = "isDeleteAll", required = false) Boolean isDeleteAll,
            @Parameter(description = "A boolean parameter to Update GHI stored for stores and insert GHI for new stores", required = false,
                    example = "true") @RequestParam(name = "isUpdate", required = false) Boolean isUpdate) {

        nrelService.insertUpdateStoresGHI(Objects.isNull(isUpdate) ? false : isUpdate, Objects.isNull(isDeleteAll) ? false : isDeleteAll);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
