/**
 * 
 */
package com.njevcs.pvawnings.controller;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.njevcs.pvawnings.entity.ZipCodes;
import com.njevcs.pvawnings.pojos.RestResponse;
import com.njevcs.pvawnings.service.ZipCodesService;
import com.njevcs.pvawnings.utils.Constants;
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
@RequestMapping(value = "/zip_codes", produces = "application/json")
@Api(tags = "Zip Codes Controller", description = "Zip Codes Controller")
public class ZipCodesController {

    @Autowired
    private ZipCodesService zipCodesService;

    @GetMapping
    @Operation(summary = "Get all Zip Codes")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of all Zip Codes",
                            content = {@Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ZipCodes.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<List<ZipCodes>>> getAllZipCodes() {

        String errorMessage = Constants.NO_DATA + " for Zip Codes";
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<ZipCodes> allZipCodes = zipCodesService.getAllZipCodes();

        RestResponse<List<ZipCodes>> response = new RestResponse<>();
        response.setData(allZipCodes);
        response.setTotal(!CollectionUtils.isEmpty(allZipCodes) ? allZipCodes.size() : 0);
        response.setErrorMessage(!CollectionUtils.isEmpty(allZipCodes) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/zip_code/{zip_code}")
    @Operation(summary = "Get Zip Code information using zip code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of a Zip Code",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ZipCodes.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<ZipCodes>> getByZipCode(
            @Parameter(name = "zip_code", description = "Filter by zip code", example = "11111") @PathVariable String zip_code) {

        String errorMessage = "Zip Codes not found for zip code : " + zip_code;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        ZipCodes zipCodes = zipCodesService.getByZip_Code(zip_code);

        RestResponse<ZipCodes> response = new RestResponse<>();
        response.setData(zipCodes);
        response.setTotal(Objects.nonNull(zipCodes) ? 1 : 0);
        response.setErrorMessage(Objects.nonNull(zipCodes) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/city/{city}")
    @Operation(summary = "Get all Zip Codes for a city")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of all Zip Codes in a city",
                            content = {@Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ZipCodes.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<List<ZipCodes>>> getByCity(
            @Parameter(name = "city", description = "Filter by city", example = "AAA") @PathVariable String city) {

        String errorMessage = Constants.NO_DATA + " of Zip Codes for city : " + city;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<ZipCodes> zipCodesByCity = zipCodesService.getByCity(city);

        RestResponse<List<ZipCodes>> response = new RestResponse<>();
        response.setData(zipCodesByCity);
        response.setTotal(!CollectionUtils.isEmpty(zipCodesByCity) ? zipCodesByCity.size() : 0);
        response.setErrorMessage(!CollectionUtils.isEmpty(zipCodesByCity) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/city_like/{city}")
    @Operation(summary = "Get all city names starting with a city name")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of all cities starting with a city name",
                            content = {
                                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = String.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<Set<String>>> getByCityStartingWith(
            @Parameter(name = "city", description = "Filter by city name starting with", example = "AAA") @PathVariable String city) {

        String errorMessage = Constants.NO_DATA + " of Zip Codes for city name starting with : " + city;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        Set<String> zipCodesByCityStartingWith = zipCodesService.getByCityStartingWith(city);

        RestResponse<Set<String>> response = new RestResponse<>();
        response.setData(zipCodesByCityStartingWith);
        response.setTotal(!CollectionUtils.isEmpty(zipCodesByCityStartingWith) ? zipCodesByCityStartingWith.size() : 0);
        response.setErrorMessage(!CollectionUtils.isEmpty(zipCodesByCityStartingWith) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/cities")
    @Operation(summary = "Get all city names")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of all cities",
                            content = {
                                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = String.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<List<ZipCodes>>> getAllCities() {

        String errorMessage = Constants.NO_DATA + " of cities.";
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<ZipCodes> zipCodes = zipCodesService.getAllCities();

        RestResponse<List<ZipCodes>> response = new RestResponse<>();
        response.setData(zipCodes);
        response.setTotal(!CollectionUtils.isEmpty(zipCodes) ? zipCodes.size() : 0);
        response.setErrorMessage(!CollectionUtils.isEmpty(zipCodes) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/county/{county}")
    @Operation(summary = "Get all Zip Codes for a county")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of all Zip Codes in a county",
                            content = {@Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ZipCodes.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<List<ZipCodes>>> getByCounty(
            @Parameter(name = "county", description = "Filter by county", example = "BBB") @PathVariable String county) {

        String errorMessage = Constants.NO_DATA + " of Zip Codes for county : " + county;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<ZipCodes> zipCodesByCounty = zipCodesService.getByCounty(county);

        RestResponse<List<ZipCodes>> response = new RestResponse<>();
        response.setData(zipCodesByCounty);
        response.setTotal(!CollectionUtils.isEmpty(zipCodesByCounty) ? zipCodesByCounty.size() : 0);
        response.setErrorMessage(!CollectionUtils.isEmpty(zipCodesByCounty) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/county_like/{county}")
    @Operation(summary = "Get all county names starting with a county name")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of all counties starting with a county name",
                            content = {
                                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = String.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<Set<String>>> getByCountyStartingWith(
            @Parameter(name = "county", description = "Filter by county name starting with", example = "BBB") @PathVariable String county) {

        String errorMessage = Constants.NO_DATA + " of Zip Codes for county name starting with : " + county;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        Set<String> zipCodesByCountyStartingWith = zipCodesService.getByCountyStartingWith(county);

        RestResponse<Set<String>> response = new RestResponse<>();
        response.setData(zipCodesByCountyStartingWith);
        response.setTotal(!CollectionUtils.isEmpty(zipCodesByCountyStartingWith) ? zipCodesByCountyStartingWith.size() : 0);
        response.setErrorMessage(!CollectionUtils.isEmpty(zipCodesByCountyStartingWith) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    @Operation(summary = "Create Zip Code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Zip Code with information created",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ZipCodes.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<ZipCodes>> createZipCode(
            @ApiParam(value = "Zip Codes object that needs to be created") @RequestBody ZipCodes zipCodes) {

        String errorMessage = "Zip Codes not created for zip code : " + zipCodes.getZipCode() + ", city : " + zipCodes.getCity() + ", and county : "
                + zipCodes.getCounty();
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        ZipCodes newZipCodes = zipCodesService.createZipCodes(zipCodes.getZipCode(), zipCodes);

        RestResponse<ZipCodes> response = new RestResponse<>();
        response.setData(newZipCodes);
        response.setTotal(Objects.nonNull(newZipCodes) ? 1 : 0);
        response.setErrorMessage(Objects.nonNull(newZipCodes) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    @Operation(summary = "Update Zip Code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Zip Code information updated",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ZipCodes.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<ZipCodes>> updatZipCode(
            @ApiParam(value = "Zip Codes object that needs to be updated") @RequestBody ZipCodes zipCodes) {

        String errorMessage = "Zip Codes not updated for zip code : " + zipCodes.getZipCode() + ", city : " + zipCodes.getCity() + ", and county : "
                + zipCodes.getCounty();
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        ZipCodes updatedZipCodes = zipCodesService.updateZipCodes(zipCodes.getZipCode(), zipCodes);

        RestResponse<ZipCodes> response = new RestResponse<>();
        response.setData(updatedZipCodes);
        response.setTotal(Objects.nonNull(updatedZipCodes) ? 1 : 0);
        response.setErrorMessage(Objects.nonNull(updatedZipCodes) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/zip_code/{zip_code}")
    @Operation(summary = "Delete Zip Codes using zip code")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Zip Codes deleted by zip code"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<HttpStatus> removeZipCode(
            @Parameter(name = "zip_code", description = "Delete by zip code", example = "11111") @PathVariable String zip_code) {

        zipCodesService.deleteZipCodes(zip_code);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/city/{city}")
    @Operation(summary = "Delete Zip Codes for a city")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Zip Codes for a city deleted"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<HttpStatus> removeCity(
            @Parameter(name = "city", description = "Delete by city name", example = "AAA") @PathVariable String city) {

        zipCodesService.deleteCity(city);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/county/{county}")
    @Operation(summary = "Delete Zip Codes for a county")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Zip Codes for a county deleted"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<HttpStatus> removeCounty(
            @Parameter(name = "county", description = "Delete by county name", example = "BBB") @PathVariable String county) {

        zipCodesService.deleteCounty(county);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
