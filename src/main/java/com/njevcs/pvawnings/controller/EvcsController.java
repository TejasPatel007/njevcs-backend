/**
 * 
 */
package com.njevcs.pvawnings.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.njevcs.pvawnings.entity.Evcs;
import com.njevcs.pvawnings.pojos.RestResponse;
import com.njevcs.pvawnings.service.EvcsService;
import com.njevcs.pvawnings.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author patel
 *
 *         Nov 14, 2024
 */
@RestController
@RequestMapping(value = "/evcs", produces = "application/json")
@Api(tags = "EVCS Controller", description = "Electric Vehicle Charging Station Controller")
public class EvcsController {

    private static final String OF_EVCS_FOR_ZIP_CODE = " of EVCS for zip code : ";

    @Autowired
    private EvcsService evcsService;

    @GetMapping
    @Operation(summary = "Get all EVCS")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of all EVCS",
                            content = {
                                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Evcs.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<List<Evcs>>> getAllEvcs() {

        String errorMessage = Constants.NO_DATA + " for EVCS";
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<Evcs> allEvcs = evcsService.getAllEvcs();

        RestResponse<List<Evcs>> response = new RestResponse<>();
        response.setData(allEvcs);
        response.setTotal(!CollectionUtils.isEmpty(allEvcs) ? allEvcs.size() : 0);
        response.setErrorMessage(!CollectionUtils.isEmpty(allEvcs) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get EVCS by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get EVCS for an Id",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Evcs.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<Evcs>> getEvcsById(
            @Parameter(name = "id", description = "Filter by Id", example = "1") @PathVariable Long id) {

        String errorMessage = "EVCS not found for ID : " + id;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        Evcs evcs = evcsService.getEvcsById(id);

        RestResponse<Evcs> response = new RestResponse<>();
        response.setData(evcs);
        response.setTotal(Objects.nonNull(evcs) ? 1 : 0);
        response.setErrorMessage(Objects.nonNull(evcs) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/zip_code/{zip_code}")
    @Operation(summary = "Get EVCS for a zip code")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of all EVCS for a zip code",
                            content = {
                                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Evcs.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<List<Evcs>>> getEvcsByZipCode(
            @Parameter(name = "zip_code", description = "Filter by zip code", example = "11111") @PathVariable String zip_code) {

        String errorMessage = Constants.NO_DATA + OF_EVCS_FOR_ZIP_CODE + zip_code;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<Evcs> evcsByZip_Code = evcsService.getEvcsByZip_Code(zip_code);

        RestResponse<List<Evcs>> response = new RestResponse<>();
        response.setData(evcsByZip_Code);
        response.setTotal(!CollectionUtils.isEmpty(evcsByZip_Code) ? evcsByZip_Code.size() : 0);
        response.setErrorMessage(!CollectionUtils.isEmpty(evcsByZip_Code) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/city/{city}")
    @Operation(summary = "Get EVCS for a city")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of all EVCS for a city",
                            content = {
                                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Evcs.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<List<Evcs>>> getEvcsByCity(
            @Parameter(name = "city", description = "Filter by city", example = "AAAAA") @PathVariable String city) {

        String errorMessage = Constants.NO_DATA + " of EVCS for city : " + city;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<Evcs> evcsByCity = evcsService.getEvcsByCity(city);

        RestResponse<List<Evcs>> response = new RestResponse<>();
        response.setData(evcsByCity);
        response.setTotal(!CollectionUtils.isEmpty(evcsByCity) ? evcsByCity.size() : 0);
        response.setErrorMessage(!CollectionUtils.isEmpty(evcsByCity) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/county/{county}")
    @Operation(summary = "Get EVCS for a county")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of all EVCS for a county",
                            content = {
                                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Evcs.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<List<Evcs>>> getEvcsByCounty(
            @Parameter(name = "county", description = "Filter by county", example = "BBBBB") @PathVariable String county) {

        String errorMessage = Constants.NO_DATA + " of EVCS for county : " + county;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<Evcs> evcsByCounty = evcsService.getEvcsByCounty(county);

        RestResponse<List<Evcs>> response = new RestResponse<>();
        response.setData(evcsByCounty);
        response.setTotal(!CollectionUtils.isEmpty(evcsByCounty) ? evcsByCounty.size() : 0);
        response.setErrorMessage(!CollectionUtils.isEmpty(evcsByCounty) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Get EVCS by name")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of all EVCS for a name",
                            content = {
                                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Evcs.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<List<Evcs>>> getEvcsByName(
            @Parameter(name = "name", description = "Filter by name", example = "station 1") @PathVariable String name) {

        String errorMessage = Constants.NO_DATA + " of EVCS for name : " + name;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<Evcs> evcsByName = evcsService.getEvcsByName(name);

        RestResponse<List<Evcs>> response = new RestResponse<>();
        response.setData(evcsByName);
        response.setTotal(!CollectionUtils.isEmpty(evcsByName) ? evcsByName.size() : 0);
        response.setErrorMessage(!CollectionUtils.isEmpty(evcsByName) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/zip_code_name/{zip_code}/{name}")
    @Operation(summary = "Get EVCS using zip code and name")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of all EVCS for a zip code and name",
                            content = {
                                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Evcs.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<List<Evcs>>> getEvcsByZipCodeAndName(
            @Parameter(name = "zip_code", description = "Filter by zip code", example = "11111") @PathVariable String zip_code,
            @Parameter(name = "name", description = "Filter by name", example = "station 1") @PathVariable String name) {

        String errorMessage = Constants.NO_DATA + OF_EVCS_FOR_ZIP_CODE + zip_code + ", and name : " + name;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<Evcs> evcsByZipCodeAndName = evcsService.getEvcsByZipCodeAndName(zip_code, name);

        RestResponse<List<Evcs>> response = new RestResponse<>();
        response.setData(evcsByZipCodeAndName);
        response.setTotal(!CollectionUtils.isEmpty(evcsByZipCodeAndName) ? evcsByZipCodeAndName.size() : 0);
        response.setErrorMessage(!CollectionUtils.isEmpty(evcsByZipCodeAndName) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/zip_code_address/{zip_code}/{address}")
    @Operation(summary = "Get EVCS using a zip code and address")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of all EVCS for a zip code and address",
                            content = {
                                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Evcs.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<List<Evcs>>> getEvcsByZipCodeAndAddress(
            @Parameter(name = "zip_code", description = "Filter by zip code", example = "11111") @PathVariable String zip_code,
            @Parameter(name = "address", description = "Filter by address", example = "1 Lincoln Ave") @PathVariable String address) {

        String errorMessage = Constants.NO_DATA + OF_EVCS_FOR_ZIP_CODE + zip_code + ", and address : " + address;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<Evcs> evcsByZipCodeAndAddress = evcsService.getEvcsByZipCodeAndAddress(zip_code, address);

        RestResponse<List<Evcs>> response = new RestResponse<>();
        response.setData(evcsByZipCodeAndAddress);
        response.setTotal(!CollectionUtils.isEmpty(evcsByZipCodeAndAddress) ? evcsByZipCodeAndAddress.size() : 0);
        response.setErrorMessage(!CollectionUtils.isEmpty(evcsByZipCodeAndAddress) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/connector_types")
    @Operation(summary = "Get EVCS using available connector types")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of all EVCS with specific connector types",
                            content = {
                                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Evcs.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<List<Evcs>>> getEvcsByConnectorTypes(@Parameter(name = "types", description = "Connector Types",
            in = ParameterIn.QUERY, array = @ArraySchema(schema = @Schema(type = "string")), example = "TESLA,J1772") @RequestParam String types) {

        String errorMessage = Constants.NO_DATA + " of EVCS for connector types : " + types;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<String> typesList = Arrays.asList(types.split(","));
        List<Evcs> evcsByConnectorTypes = evcsService.getEvcsByConnectorTypes(typesList);

        RestResponse<List<Evcs>> response = new RestResponse<>();
        response.setData(evcsByConnectorTypes);
        response.setTotal(!CollectionUtils.isEmpty(evcsByConnectorTypes) ? evcsByConnectorTypes.size() : 0);
        response.setErrorMessage(!CollectionUtils.isEmpty(evcsByConnectorTypes) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/points")
    @Operation(summary = "Get EVCS using number of charging points available")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "level1", value = "Number of level1 chargers", required = false, dataType = "string",
                    dataTypeClass = String.class, paramType = "query", example = "2"),
            @ApiImplicitParam(name = "level2", value = "Number of level2 chargers", required = false, dataType = "string",
                    dataTypeClass = String.class, paramType = "query", example = "25"),
            @ApiImplicitParam(name = "dc_fast", value = "Number of dc fast chargers", required = false, dataType = "string",
                    dataTypeClass = String.class, paramType = "query", example = "35")})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of all EVCS with different number of charging points",
                            content = {
                                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Evcs.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<List<Evcs>>> getEvcsByPoints(@ApiIgnore @RequestParam HashMap<String, String> pointsMap) {

        StringBuilder errorMessageBuilder = new StringBuilder();
        errorMessageBuilder.append(Constants.NO_DATA).append(" of EVCS for number of");
        pointsMap.forEach((key, value) -> errorMessageBuilder.append(" ").append(key).append(" >= ").append(value).append(" or"));
        String errorMessage = errorMessageBuilder.toString();
        errorMessage = errorMessage.substring(0, errorMessage.length() - 2) + "points.";
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<Evcs> evcsByPoints = evcsService.getEvcsByPoints(pointsMap);

        RestResponse<List<Evcs>> response = new RestResponse<>();
        response.setData(evcsByPoints);
        response.setTotal(!CollectionUtils.isEmpty(evcsByPoints) ? evcsByPoints.size() : 0);
        response.setErrorMessage(!CollectionUtils.isEmpty(evcsByPoints) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{zip_code}/connector_types")
    @Operation(summary = "Get EVCS using a zip code and connector types")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of all EVCS for a zip code and available connector types",
                            content = {
                                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Evcs.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<List<Evcs>>> getEvcsByZipCodeAndConnectorTypes(
            @Parameter(name = "zip_code", description = "Filter by zip code", example = "11111") @PathVariable String zip_code,
            @Parameter(name = "types", description = "Connector Types", in = ParameterIn.QUERY,
                    array = @ArraySchema(schema = @Schema(type = "string")), example = "TESLA,J1772") @RequestParam String types) {

        String errorMessage = Constants.NO_DATA + OF_EVCS_FOR_ZIP_CODE + zip_code + ", and connector types : " + types;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<String> typesList = Arrays.asList(types.split(","));
        List<Evcs> evcsByZipCodeAndConnectorTypes = evcsService.getEvcsByZipCodeAndConnectorTypes(zip_code, typesList);

        RestResponse<List<Evcs>> response = new RestResponse<>();
        response.setData(evcsByZipCodeAndConnectorTypes);
        response.setTotal(!CollectionUtils.isEmpty(evcsByZipCodeAndConnectorTypes) ? evcsByZipCodeAndConnectorTypes.size() : 0);
        response.setErrorMessage(!CollectionUtils.isEmpty(evcsByZipCodeAndConnectorTypes) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{zip_code}/points")
    @Operation(summary = "Get EVCS using a zip code and number of charging points")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "level1", value = "Number of level1 chargers", required = false, dataType = "string",
                    dataTypeClass = String.class, paramType = "query", example = "2"),
            @ApiImplicitParam(name = "level2", value = "Number of level2 chargers", required = false, dataType = "string",
                    dataTypeClass = String.class, paramType = "query", example = "25"),
            @ApiImplicitParam(name = "dc_fast", value = "Number of dc fast chargers", required = false, dataType = "string",
                    dataTypeClass = String.class, paramType = "query", example = "35")})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of all EVCS for a zip code and number of charging points",
                            content = {
                                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Evcs.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<List<Evcs>>> getEvcsByZipCodeAndPoints(
            @Parameter(name = "zip_code", description = "Filter by zip code", example = "11111") @PathVariable String zip_code,
            @ApiIgnore @RequestParam HashMap<String, String> pointsMap) {

        StringBuilder errorMessageBuilder = new StringBuilder();
        errorMessageBuilder.append(Constants.NO_DATA).append(OF_EVCS_FOR_ZIP_CODE).append(zip_code).append(", and number of");
        pointsMap.forEach((key, value) -> errorMessageBuilder.append(" ").append(key).append(" >= ").append(value).append(" or"));
        String errorMessage = errorMessageBuilder.toString();
        errorMessage = errorMessage.substring(0, errorMessage.length() - 2) + "points.";
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<Evcs> evcsByZipCodeAndPoints = evcsService.getEvcsByZipCodeAndPoints(zip_code, pointsMap);

        RestResponse<List<Evcs>> response = new RestResponse<>();
        response.setData(evcsByZipCodeAndPoints);
        response.setTotal(!CollectionUtils.isEmpty(evcsByZipCodeAndPoints) ? evcsByZipCodeAndPoints.size() : 0);
        response.setErrorMessage(!CollectionUtils.isEmpty(evcsByZipCodeAndPoints) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    @Operation(summary = "Create EVCS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "EVCS created",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Evcs.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<Evcs>> createEvcs(@ApiParam(value = "EVCS object that needs to be created") @RequestBody Evcs evcs) {

        String errorMessage = "EVCS not created for zip code : " + evcs.getZipCode() + ", name : " + evcs.getName() + ", address : "
                + evcs.getAddress() + ", latitude : " + evcs.getLatitude() + ", and longitude : " + evcs.getLongitude();
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        Evcs newEvcs = evcsService.createEvcs(evcs);

        RestResponse<Evcs> response = new RestResponse<>();
        response.setData(newEvcs);
        response.setTotal(Objects.nonNull(newEvcs) ? 1 : 0);
        response.setErrorMessage(Objects.nonNull(newEvcs) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    @Operation(summary = "Update EVCS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "EVCS updated",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Evcs.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<Evcs>> updatEvcs(@ApiParam(value = "EVCS object that needs to be updated") @RequestBody Evcs evcs) {

        String errorMessage = "EVCS not updated for zip code : " + evcs.getZipCode() + ", name : " + evcs.getName() + ", address : "
                + evcs.getAddress() + ", latitude : " + evcs.getLatitude() + ", and longitude : " + evcs.getLongitude();
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        Evcs updatedEvcs = evcsService.updateEvcs(evcs.getId(), evcs);

        RestResponse<Evcs> response = new RestResponse<>();
        response.setData(updatedEvcs);
        response.setTotal(Objects.nonNull(updatedEvcs) ? 1 : 0);
        response.setErrorMessage(Objects.nonNull(updatedEvcs) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/zip_code/{zip_code}")
    @Operation(summary = "Delete EVCS using a zip code")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "EVCS deleted using a zip code"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<HttpStatus> removeEvcsByZipCode(
            @Parameter(name = "zip_code", description = "Delete by zip code", example = "11111") @PathVariable String zip_code) {

        evcsService.deleteEvcsByZipCode(zip_code);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/name/{name}")
    @Operation(summary = "Delete EVCS by name")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "EVCS deleted with a name"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<HttpStatus> removeEvcsByName(
            @Parameter(name = "name", description = "Delete by name", example = "station 1") @PathVariable String name) {

        evcsService.deleteEvcsByName(name);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete EVCS by Id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "EVCS deleted with an Id"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<HttpStatus> removeEvcsById(@Parameter(name = "id", description = "Delete by Id", example = "1") @PathVariable Long id) {

        evcsService.deleteEvcsById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
