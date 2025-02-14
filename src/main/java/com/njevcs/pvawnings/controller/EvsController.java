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
import com.njevcs.pvawnings.entity.Evs;
import com.njevcs.pvawnings.pojos.RestResponse;
import com.njevcs.pvawnings.service.EvsService;
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
@RequestMapping(value = "/evs", produces = "application/json")
@Api(tags = "EVs Controller", description = "Electric Vehicles Controller")
public class EvsController {

    @Autowired
    private EvsService evsService;

    @GetMapping
    @Operation(summary = "Get all EVs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all EVs",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Evs.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<Map<String, Object>> getAllEvs() {

        String errorMessage = Constants.NO_DATA + " for Electric Vehicles";
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<Map<String, Object>> allEvs = evsService.getAllEvs();

        return ResponseEntity.status(HttpStatus.OK).body(Utility.generateResponse(true, allEvs, null, errorMessage));
    }

    @GetMapping("/zip_code/{zip_code}")
    @Operation(summary = "Get all EVs for a zip code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all EVs for a zip code",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Evs.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<Map<String, Object>> getEvsByZipCode(
            @Parameter(name = "zip_code", description = "Filter by zip code", example = "11111") @PathVariable String zip_code) {

        String errorMessage = "Electric Vehicles not found for zip code : " + zip_code;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        Map<String, Object> evs = evsService.getEvsByZip_Code(zip_code);

        return ResponseEntity.status(HttpStatus.OK).body(Utility.generateResponse(false, null, evs, errorMessage));
    }

    @GetMapping("/city/{city}")
    @Operation(summary = "Get all EVs for a city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all EVs for a city",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Evs.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<Map<String, Object>> getEvsByCity(
            @Parameter(name = "city", description = "Filter by city", example = "AAAAA") @PathVariable String city) {

        String errorMessage = Constants.NO_DATA + " of Electric Vehicles for city : " + city;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        Map<String, Object> evsByCity = evsService.getEvsByCity(city);

        return ResponseEntity.status(HttpStatus.OK).body(Utility.generateResponse(false, null, evsByCity, errorMessage));
    }

    @GetMapping("/cities")
    @Operation(summary = "Get number of EVs for all cities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all cities with number of EVs",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Evs.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<Map<String, Object>> getEVsForAllCities() {

        String errorMessage = Constants.NO_DATA + " of EVs for cities.";
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<Map<String, Object>> evsForAllCities = evsService.getEvsForAllCities();

        return ResponseEntity.status(HttpStatus.OK).body(Utility.generateResponse(true, evsForAllCities, null, errorMessage));
    }

    @GetMapping("/county/{county}")
    @Operation(summary = "Get all EVs for a county")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all EVs for a county",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Evs.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<Map<String, Object>> getEvsByCounty(
            @Parameter(name = "county", description = "Filter by county", example = "BBBBB") @PathVariable String county) {

        String errorMessage = Constants.NO_DATA + " of Electric Vehicles for county : " + county;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        Map<String, Object> evsByCounty = evsService.getEvsByCounty(county);

        return ResponseEntity.status(HttpStatus.OK).body(Utility.generateResponse(false, null, evsByCounty, errorMessage));
    }

    @GetMapping("/counties")
    @Operation(summary = "Get number of EVs for all counties")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all counties with number of EVs",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Evs.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<Map<String, Object>> getEVsForAllCounties() {

        String errorMessage = Constants.NO_DATA + " of EVs for counties.";
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<Map<String, Object>> evsForAllCounties = evsService.getEvsForAllCounties();

        return ResponseEntity.status(HttpStatus.OK).body(Utility.generateResponse(true, evsForAllCounties, null, errorMessage));
    }

    @PostMapping
    @Operation(summary = "Create EVs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "EVs created",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Evs.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<Evs>> createEvs(@ApiParam(value = "EVs object that needs to be created") @RequestBody Evs evs) {

        String errorMessage = "EVs not created for zip code : " + evs.getZipCode() + ", and income : " + evs.getNumberOfEvs();
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        Evs newEvs = evsService.createEvs(evs.getZipCode(), evs);

        RestResponse<Evs> response = new RestResponse<>();
        response.setData(newEvs);
        response.setTotal(Objects.nonNull(newEvs) ? 1 : 0);
        response.setErrorMessage(Objects.nonNull(newEvs) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    @Operation(summary = "Update EVs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "EVs updated",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Evs.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<Evs>> updatEvs(@ApiParam(value = "EVs object that needs to be updated") @RequestBody Evs evs) {

        String errorMessage = "EVs not updated for zip code : " + evs.getZipCode() + ", and income : " + evs.getNumberOfEvs();
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        Evs updatedEvs = evsService.updateEvs(evs.getZipCode(), evs);

        RestResponse<Evs> response = new RestResponse<>();
        response.setData(updatedEvs);
        response.setTotal(Objects.nonNull(updatedEvs) ? 1 : 0);
        response.setErrorMessage(Objects.nonNull(updatedEvs) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{zip_code}")
    @Operation(summary = "Delete EVs for a zip code")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Deleted EVs for a zip code"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<HttpStatus> removeEvs(
            @Parameter(name = "zip_code", description = "Delete by zip code", example = "11111") @PathVariable String zip_code) {

        evsService.deleteEvs(zip_code);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
