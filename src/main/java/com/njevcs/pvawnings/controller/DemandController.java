/**
 * 
 */
package com.njevcs.pvawnings.controller;

import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.njevcs.pvawnings.pojos.DemandCalculationDTO;
import com.njevcs.pvawnings.service.DemandService;
import com.njevcs.pvawnings.utils.Constants;
import com.njevcs.pvawnings.utils.Utility;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * @author patel
 *
 *         Nov 14, 2024
 */
@RestController
@RequestMapping(value = "/demand", produces = "application/json")
@Api(tags = "Energy Demand Controller", description = "Energy Demand Controller")
public class DemandController {

    @Autowired
    private DemandService demandService;

    @GetMapping("/city/{city}")
    @Operation(summary = "Get all details of energy demand for a city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all details of energy demand for a city",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DemandCalculationDTO.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<Map<String, Object>> getDemandByCity(
            @Parameter(name = "city", description = "Filter by city", example = "AAAAA") @PathVariable String city) {

        String errorMessage = Constants.NO_DATA + " of Energy Demand Analysis for city : " + city;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        DemandCalculationDTO demandByCity = demandService.getDemandByCity(city);

        return ResponseEntity.status(HttpStatus.OK).body(Utility.generateResponse(false, null, demandByCity, errorMessage));
    }

    @GetMapping("/cities")
    @Operation(summary = "Get all details of energy demand for all cities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all details of energy demand for all cities",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DemandCalculationDTO.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<Map<String, Object>> getDemandForAllCities() {

        String errorMessage = Constants.NO_DATA + " of Energy Demand Analysis for cities.";
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<DemandCalculationDTO> demandForAllCities = demandService.getDemandForAllCities();

        return ResponseEntity.status(HttpStatus.OK).body(Utility.generateResponse(true, demandForAllCities, null, errorMessage));
    }

    @GetMapping("/top10cities")
    @Operation(summary = "Get details of energy demand for top 10 energy deficit cities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of details of energy demand for top 10 energy deficit cities",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DemandCalculationDTO.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<Map<String, Object>> getTop10EnergyDeficitCities() {

        String errorMessage = Constants.NO_DATA + " of Energy Demand Analysis for cities.";
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<DemandCalculationDTO> demandForAllCities = demandService.getTop10EnergyDeficitCities();

        return ResponseEntity.status(HttpStatus.OK).body(Utility.generateResponse(true, demandForAllCities, null, errorMessage));
    }

    @GetMapping("/top10cities/county/{county}")
    @Operation(summary = "Get details of energy demand for top 10 energy deficit cities for a county")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of details of energy demand for top 10 energy deficit cities for a county",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DemandCalculationDTO.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<Map<String, Object>> getTop10EnergyDeficitCitiesForCounty(
            @Parameter(name = "county", description = "Filter by county", example = "BBBBB") @PathVariable String county) {

        String errorMessage = Constants.NO_DATA + " of Energy Demand Analysis for county : " + county;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<DemandCalculationDTO> demandForAllCities = demandService.getTop10EnergyDeficitCitiesForCounty(county);

        return ResponseEntity.status(HttpStatus.OK).body(Utility.generateResponse(true, demandForAllCities, null, errorMessage));
    }

    @GetMapping("/county/{county}")
    @Operation(summary = "Get all details of energy demand for a county")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all details of energy demand for a county",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DemandCalculationDTO.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<Map<String, Object>> getDemandByCounty(
            @Parameter(name = "county", description = "Filter by county", example = "BBBBB") @PathVariable String county) {

        String errorMessage = Constants.NO_DATA + " of Energy Demand Analysis for county : " + county;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        DemandCalculationDTO demandByCounty = demandService.getDemandByCounty(county);

        return ResponseEntity.status(HttpStatus.OK).body(Utility.generateResponse(false, null, demandByCounty, errorMessage));
    }

    @GetMapping("/counties")
    @Operation(summary = "Get all details of energy demand for all counties")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all details of energy demand for all counties",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DemandCalculationDTO.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<Map<String, Object>> getDemandForAllCounties() {

        String errorMessage = Constants.NO_DATA + " of Energy Demand Analysis for counties.";
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<DemandCalculationDTO> demandForAllCounties = demandService.getDemandForAllCounties();

        return ResponseEntity.status(HttpStatus.OK).body(Utility.generateResponse(true, demandForAllCounties, null, errorMessage));
    }

    @GetMapping("/top10counties")
    @Operation(summary = "Get details of energy demand for top 10 energy deficit counties")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of details of energy demand for top 10 energy deficit counties",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DemandCalculationDTO.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<Map<String, Object>> getTop10EnergyDeficitCounties() {

        String errorMessage = Constants.NO_DATA + " of Energy Demand Analysis for counties.";
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<DemandCalculationDTO> demandForAllCities = demandService.getTop10EnergyDeficitCounties();

        return ResponseEntity.status(HttpStatus.OK).body(Utility.generateResponse(true, demandForAllCities, null, errorMessage));
    }

}
