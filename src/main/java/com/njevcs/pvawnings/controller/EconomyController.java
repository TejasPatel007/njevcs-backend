/**
 * 
 */
package com.njevcs.pvawnings.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.njevcs.pvawnings.entity.Economy;
import com.njevcs.pvawnings.pojos.RestResponse;
import com.njevcs.pvawnings.service.EconomyService;
import com.njevcs.pvawnings.utils.Constants;
import com.njevcs.pvawnings.utils.Utility;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
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
@RequestMapping(value = "/economy", produces = "application/json")
@Api(tags = "Economy Controller", description = "Median Household Income Controller")
public class EconomyController {

    @Autowired
    private EconomyService economyService;

    @GetMapping
    @Operation(summary = "Get median household income")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of all median household income for a zip code",
                            content = {@Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Economy.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<Map<String, Object>> getAllEconomy() {

        String errorMessage = Constants.NO_DATA + " for Median Household Income";
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<Map<String, Object>> allEconomy = economyService.getAllEconomy();

        return ResponseEntity.status(HttpStatus.OK).body(Utility.generateResponse(true, allEconomy, null, errorMessage));
    }

    @GetMapping("/zip_code/{zip_code}")
    @Operation(summary = "Get median household income for a zip code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Median household income for a zip code",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Economy.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<Map<String, Object>> getEconomyByZipCode(
            @Parameter(name = "zip_code", description = "Filter by zip code", example = "11111") @PathVariable String zip_code) {

        String errorMessage = "Economy not found for zip code : " + zip_code;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        Map<String, Object> economy = economyService.getEconomyByZip_Code(zip_code);

        return ResponseEntity.status(HttpStatus.OK).body(Utility.generateResponse(false, null, economy, errorMessage));
    }

    @GetMapping("/city/{city}")
    @Operation(summary = "Get median household income for a city")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Median household income for a city",
                            content = {@Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Economy.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<Map<String, Object>> getEconomyByCity(
            @Parameter(name = "city", description = "Filter by city", example = "AAAAA") @PathVariable String city) {

        String errorMessage = Constants.NO_DATA + " of Median Household Income for city : " + city;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        Map<String, Object> economyByCity = economyService.getEconomyByCity(city);

        return ResponseEntity.status(HttpStatus.OK).body(Utility.generateResponse(false, null, economyByCity, errorMessage));
    }

    @GetMapping("/cities")
    @Operation(summary = "Get median household income for all cities")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Median household income for all cities",
                            content = {@Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Economy.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<Map<String, Object>> getEconomyForAllCities() {

        String errorMessage = Constants.NO_DATA + " of Median Household Income for cities.";
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<Map<String, Object>> economyForAllCities = economyService.getEconomyForAllCities();

        return ResponseEntity.status(HttpStatus.OK).body(Utility.generateResponse(true, economyForAllCities, null, errorMessage));
    }

    @GetMapping("/county/{county}")
    @Operation(summary = "Get median household income for a county")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Median household income for a county",
                            content = {@Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Economy.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<Map<String, Object>> getEconomyByCounty(
            @Parameter(name = "county", description = "Filter by county", example = "BBBBB") @PathVariable String county) {

        String errorMessage = Constants.NO_DATA + " of Median Household Income for county : " + county;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        Map<String, Object> economyByCounty = economyService.getEconomyByCounty(county);

        return ResponseEntity.status(HttpStatus.OK).body(Utility.generateResponse(false, null, economyByCounty, errorMessage));
    }

    @GetMapping("/counties")
    @Operation(summary = "Get median household income for all counties")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Median household income for all counties",
                            content = {@Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Economy.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<Map<String, Object>> getEconomyForAllCounties() {

        String errorMessage = Constants.NO_DATA + " of Median Household Income for counties.";
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<Map<String, Object>> economyForAllCounties = economyService.getEconomyForAllCounties();

        return ResponseEntity.status(HttpStatus.OK).body(Utility.generateResponse(true, economyForAllCounties, null, errorMessage));
    }

    @PostMapping
    @Operation(summary = "Add median household income for a zip code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Median household income for a zip code Created",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Economy.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<Economy>> createEconomy(
            @ApiParam(value = "Economy object that needs to be created") @RequestBody Economy economy) {

        String errorMessage = "Economy not created for zip code : " + economy.getZipCode() + ", and income : " + economy.getIncome();
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        Economy newEconomy = economyService.createEconomy(economy.getZipCode(), economy);

        RestResponse<Economy> response = new RestResponse<>();
        response.setData(newEconomy);
        response.setTotal(Objects.nonNull(newEconomy) ? 1 : 0);
        response.setErrorMessage(Objects.nonNull(newEconomy) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    @Operation(summary = "Update median household income for a zip code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Median household income for a zip code updated",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Economy.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<Economy>> updatEconomy(
            @ApiParam(value = "Economy object that needs to be updated") @RequestBody Economy economy) {

        String errorMessage = "Economy not updated for zip code : " + economy.getZipCode() + ", and income : " + economy.getIncome();
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        Economy updatedEconomy = economyService.updateEconomy(economy.getZipCode(), economy);

        RestResponse<Economy> response = new RestResponse<>();
        response.setData(updatedEconomy);
        response.setTotal(Objects.nonNull(updatedEconomy) ? 1 : 0);
        response.setErrorMessage(Objects.nonNull(updatedEconomy) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{zip_code}")
    @Operation(summary = "Delete median household income for a zip code")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Median household income for a zip code Deleted"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<HttpStatus> removeEconomy(
            @Parameter(name = "zip_code", description = "Delete by zip code", example = "11111") @PathVariable String zip_code) {

        economyService.deleteEconomy(zip_code);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
