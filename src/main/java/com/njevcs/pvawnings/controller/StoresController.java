/**
 * 
 */
package com.njevcs.pvawnings.controller;

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
import org.springframework.web.bind.annotation.RestController;
import com.njevcs.pvawnings.entity.Stores;
import com.njevcs.pvawnings.pojos.RestResponse;
import com.njevcs.pvawnings.service.StoresService;
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
@RequestMapping(value = "/stores", produces = "application/json")
@Api(tags = "Stores Controller", description = "Retail Stores Controller")
public class StoresController {

    private static final String SQUARE_METER = " square meter.";
    private static final String OF_STORES_FOR_ZIP_CODE = " of Stores for zip code : ";

    @Autowired
    private StoresService storesService;

    @GetMapping
    @Operation(summary = "Get all Stores")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of all Stores",
                            content = {
                                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Stores.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<List<Stores>>> getAllStores() {

        String errorMessage = Constants.NO_DATA + " for Stores";
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<Stores> allStores = storesService.getAllStores();

        RestResponse<List<Stores>> response = new RestResponse<>();
        response.setData(allStores);
        response.setTotal(!CollectionUtils.isEmpty(allStores) ? allStores.size() : 0);
        response.setErrorMessage(!CollectionUtils.isEmpty(allStores) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a Store by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Store by Id",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Stores.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<Stores>> getStoresById(
            @Parameter(name = "id", description = "Filter by id", example = "1") @PathVariable Long id) {

        String errorMessage = "Stores not found for ID : " + id;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        Stores stores = storesService.getStoresById(id);

        RestResponse<Stores> response = new RestResponse<>();
        response.setData(stores);
        response.setTotal(Objects.nonNull(stores) ? 1 : 0);
        response.setErrorMessage(Objects.nonNull(stores) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/zip_code/{zip_code}")
    @Operation(summary = "Get all Stores in a zip code")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of all Stores in a zip code",
                            content = {
                                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Stores.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<List<Stores>>> getStoresByZipCode(
            @Parameter(name = "zip_code", description = "Filter by zip code", example = "11111") @PathVariable String zip_code) {

        String errorMessage = Constants.NO_DATA + OF_STORES_FOR_ZIP_CODE + zip_code;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<Stores> storesByZip_Code = storesService.getStoresByZip_Code(zip_code);

        RestResponse<List<Stores>> response = new RestResponse<>();
        response.setData(storesByZip_Code);
        response.setTotal(!CollectionUtils.isEmpty(storesByZip_Code) ? storesByZip_Code.size() : 0);
        response.setErrorMessage(!CollectionUtils.isEmpty(storesByZip_Code) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/city/{city}")
    @Operation(summary = "Get all Stores in a city")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of all Stores in a city",
                            content = {
                                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Stores.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<List<Stores>>> getStoresByCity(
            @Parameter(name = "city", description = "Filter by city", example = "AAAAA") @PathVariable String city) {

        String errorMessage = Constants.NO_DATA + " of Stores for city : " + city;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<Stores> storesByCity = storesService.getStoresByCity(city);

        RestResponse<List<Stores>> response = new RestResponse<>();
        response.setData(storesByCity);
        response.setTotal(!CollectionUtils.isEmpty(storesByCity) ? storesByCity.size() : 0);
        response.setErrorMessage(!CollectionUtils.isEmpty(storesByCity) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/county/{county}")
    @Operation(summary = "Get all Stores in a county")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of all Stores in a county",
                            content = {
                                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Stores.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<List<Stores>>> getStoresByCounty(
            @Parameter(name = "county", description = "Filter by county", example = "11111") @PathVariable String county) {

        String errorMessage = Constants.NO_DATA + " of Stores for county : " + county;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<Stores> storesByCounty = storesService.getStoresByCounty(county);

        RestResponse<List<Stores>> response = new RestResponse<>();
        response.setData(storesByCounty);
        response.setTotal(!CollectionUtils.isEmpty(storesByCounty) ? storesByCounty.size() : 0);
        response.setErrorMessage(!CollectionUtils.isEmpty(storesByCounty) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Get all Stores with a name")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of all Stores by name",
                            content = {
                                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Stores.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<List<Stores>>> getStoresByName(
            @Parameter(name = "name", description = "Filter by name", example = "Costco") @PathVariable String name) {

        String errorMessage = Constants.NO_DATA + " of Stores for name : " + name;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<Stores> storesByName = storesService.getStoresByName(name);

        RestResponse<List<Stores>> response = new RestResponse<>();
        response.setData(storesByName);
        response.setTotal(!CollectionUtils.isEmpty(storesByName) ? storesByName.size() : 0);
        response.setErrorMessage(!CollectionUtils.isEmpty(storesByName) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/zip_code_name/{zip_code}/{name}")
    @Operation(summary = "Get all Stores in a zip code with name")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of all Stores in a zip code by name",
                            content = {
                                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Stores.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<List<Stores>>> getStoresByZipCodeAndName(
            @Parameter(name = "zip_code", description = "Filter by zip code", example = "11111") @PathVariable String zip_code,
            @Parameter(name = "name", description = "Filter by name", example = "Costco") @PathVariable String name) {

        String errorMessage = Constants.NO_DATA + OF_STORES_FOR_ZIP_CODE + zip_code + ", and name : " + name;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<Stores> storesByZipCodeAndName = storesService.getStoresByZipCodeAndName(zip_code, name);

        RestResponse<List<Stores>> response = new RestResponse<>();
        response.setData(storesByZipCodeAndName);
        response.setTotal(!CollectionUtils.isEmpty(storesByZipCodeAndName) ? storesByZipCodeAndName.size() : 0);
        response.setErrorMessage(!CollectionUtils.isEmpty(storesByZipCodeAndName) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/zip_code_address/{zip_code}/{address}")
    @Operation(summary = "Get all Stores in a zip code with address")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of all Stores in a zip code by address",
                            content = {
                                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Stores.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<List<Stores>>> getStoresByZipCodeAndAddress(
            @Parameter(name = "zip_code", description = "Filter by zip code", example = "11111") @PathVariable String zip_code,
            @Parameter(name = "address", description = "Filter by address", example = "1 Lincoln Ave") @PathVariable String address) {

        String errorMessage = Constants.NO_DATA + OF_STORES_FOR_ZIP_CODE + zip_code + ", and address : " + address;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<Stores> storesByZipCodeAndAddress = storesService.getStoresByZipCodeAndAddress(zip_code, address);

        RestResponse<List<Stores>> response = new RestResponse<>();
        response.setData(storesByZipCodeAndAddress);
        response.setTotal(!CollectionUtils.isEmpty(storesByZipCodeAndAddress) ? storesByZipCodeAndAddress.size() : 0);
        response.setErrorMessage(!CollectionUtils.isEmpty(storesByZipCodeAndAddress) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/parking_area/{parkingArea}")
    @Operation(summary = "Get all Stores having minimum parkig area")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of all Stores having minimum parking area",
                            content = {
                                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Stores.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<List<Stores>>> getStoresByParkingAreaGreaterThanEqual(
            @Parameter(name = "parkingArea", description = "Filter by minimum parking area of", example = "25000") @PathVariable Long parkingArea) {

        String errorMessage = Constants.NO_DATA + " of Stores with parking area >= " + parkingArea + SQUARE_METER;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<Stores> storesByParkingAreaGreaterThanEqual = storesService.getStoresByParkingAreaGreaterThanEqual(parkingArea);

        RestResponse<List<Stores>> response = new RestResponse<>();
        response.setData(storesByParkingAreaGreaterThanEqual);
        response.setTotal(!CollectionUtils.isEmpty(storesByParkingAreaGreaterThanEqual) ? storesByParkingAreaGreaterThanEqual.size() : 0);
        response.setErrorMessage(!CollectionUtils.isEmpty(storesByParkingAreaGreaterThanEqual) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/parking_area/{minParkingArea}/{maxParkingArea}")
    @Operation(summary = "Get all Stores having minimum and maximum parking area")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of all Stores having minimum and maximum parking area",
                            content = {
                                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Stores.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<List<Stores>>> getStoresByParkingAreaBetween(
            @Parameter(name = "minParkingArea", description = "Filter by minimum parking area of",
                    example = "25000") @PathVariable Long minParkingArea,
            @Parameter(name = "maxParkingArea", description = "Filter by maximum parking area of",
                    example = "30000") @PathVariable Long maxParkingArea) {

        String errorMessage = Constants.NO_DATA + " of Stores with parking area >= " + minParkingArea + " and <= " + maxParkingArea + SQUARE_METER;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<Stores> storesByParkingAreaBetween = storesService.getStoresByParkingAreaBetween(minParkingArea, maxParkingArea);

        RestResponse<List<Stores>> response = new RestResponse<>();
        response.setData(storesByParkingAreaBetween);
        response.setTotal(!CollectionUtils.isEmpty(storesByParkingAreaBetween) ? storesByParkingAreaBetween.size() : 0);
        response.setErrorMessage(!CollectionUtils.isEmpty(storesByParkingAreaBetween) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{zip_code}/parking_area/{parkingArea}")
    @Operation(summary = "Get all Stores having minimum parkig area in a zip code")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of all Stores having minimum parking area in a zip code",
                            content = {
                                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Stores.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<List<Stores>>> getaStoresByParkingAreaGreaterThanEqual(
            @Parameter(name = "zip_code", description = "Filter by zip code", example = "11111") @PathVariable String zip_code,
            @Parameter(name = "parkingArea", description = "Filter by minimum parking area of", example = "25000") @PathVariable Long parkingArea) {

        String errorMessage = Constants.NO_DATA + " of Stores with zip code : " + zip_code + " with parking area >= " + parkingArea + SQUARE_METER;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<Stores> storesByZipCodeAndParkingAreaGreaterThanEqual =
                storesService.getStoresByZipCodeAndParkingAreaGreaterThanEqual(zip_code, parkingArea);

        RestResponse<List<Stores>> response = new RestResponse<>();
        response.setData(storesByZipCodeAndParkingAreaGreaterThanEqual);
        response.setTotal(
                !CollectionUtils.isEmpty(storesByZipCodeAndParkingAreaGreaterThanEqual) ? storesByZipCodeAndParkingAreaGreaterThanEqual.size() : 0);
        response.setErrorMessage(!CollectionUtils.isEmpty(storesByZipCodeAndParkingAreaGreaterThanEqual) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{zip_code}/parking_area/{minParkingArea}/{maxParkingArea}")
    @Operation(summary = "Get all Stores having minimum and maximum parking area in a zip code")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List of all Stores having minimum and maximum parking area in a zip code",
                            content = {
                                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Stores.class)))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<List<Stores>>> getaStoresByParkingAreaBetween(
            @Parameter(name = "zip_code", description = "Filter by zip code", example = "11111") @PathVariable String zip_code,
            @Parameter(name = "minParkingArea", description = "Filter by minimum parking area of",
                    example = "25000") @PathVariable Long minParkingArea,
            @Parameter(name = "maxParkingArea", description = "Filter by maximum parking area of",
                    example = "30000") @PathVariable Long maxParkingArea) {

        String errorMessage = Constants.NO_DATA + " of Stores with zip code : " + zip_code + " with parking area >= " + minParkingArea + " and <= "
                + maxParkingArea + SQUARE_METER;
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        List<Stores> storesByZipCodeAndParkingAreaBetween =
                storesService.getStoresByZipCodeAndParkingAreaBetween(zip_code, minParkingArea, maxParkingArea);

        RestResponse<List<Stores>> response = new RestResponse<>();
        response.setData(storesByZipCodeAndParkingAreaBetween);
        response.setTotal(!CollectionUtils.isEmpty(storesByZipCodeAndParkingAreaBetween) ? storesByZipCodeAndParkingAreaBetween.size() : 0);
        response.setErrorMessage(!CollectionUtils.isEmpty(storesByZipCodeAndParkingAreaBetween) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    @Operation(summary = "Create a Store")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Store created",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Stores.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<Stores>> createStores(@ApiParam(value = "Stores object that needs to be created") @RequestBody Stores stores) {

        String errorMessage = "Stores not created for zip code : " + stores.getZipCode() + ", name : " + stores.getName() + ", address : "
                + stores.getAddress() + ", parking area : " + stores.getParkingArea() + ", latitude : " + stores.getLatitude() + ", and longitude : "
                + stores.getLongitude();
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        Stores newStores = storesService.createStores(stores);

        RestResponse<Stores> response = new RestResponse<>();
        response.setData(newStores);
        response.setTotal(Objects.nonNull(newStores) ? 1 : 0);
        response.setErrorMessage(Objects.nonNull(newStores) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    @Operation(summary = "Update a Store")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Store Updated",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Stores.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<RestResponse<Stores>> updatStores(@ApiParam(value = "Stores object that needs to be updated") @RequestBody Stores stores) {

        String errorMessage = "Stores not updated for ID : " + stores.getId() + ", zip code : " + stores.getZipCode() + ", name : " + stores.getName()
                + ", address : " + stores.getAddress() + ", parking area : " + stores.getParkingArea() + ", latitude : " + stores.getLatitude()
                + ", and longitude : " + stores.getLongitude();
        ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);

        Stores updatedStores = storesService.updateStores(stores.getId(), stores);

        RestResponse<Stores> response = new RestResponse<>();
        response.setData(updatedStores);
        response.setTotal(Objects.nonNull(updatedStores) ? 1 : 0);
        response.setErrorMessage(Objects.nonNull(updatedStores) ? null : errorMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/zip_code/{zip_code}")
    @Operation(summary = "Delete all Stores in a zip code")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Stores deleted using zip code"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<HttpStatus> removeStoresByZipCode(
            @Parameter(name = "zip_code", description = "Delete by zip code", example = "11111") @PathVariable String zip_code) {

        storesService.deleteStoresByZipCode(zip_code);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/name/{name}")
    @Operation(summary = "Delete all Stores with name")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Stores deleted by name"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<HttpStatus> removeStoresByName(
            @Parameter(name = "name", description = "Delete by name", example = "Costco") @PathVariable String name) {

        storesService.deleteStoresByName(name);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Store by Id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Store deleted by Id"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    public ResponseEntity<HttpStatus> removeStoresById(@Parameter(name = "id", description = "Delete by id", example = "1") @PathVariable Long id) {

        storesService.deleteStoresById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
