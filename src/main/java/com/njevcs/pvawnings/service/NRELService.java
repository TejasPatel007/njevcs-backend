/**
 * 
 */
package com.njevcs.pvawnings.service;

import java.util.List;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import com.njevcs.pvawnings.WebClientConfig;
import com.njevcs.pvawnings.entity.Stores;
import com.njevcs.pvawnings.entity.StoresGHI;
import com.njevcs.pvawnings.pojos.SolarResourceResponse;
import com.njevcs.pvawnings.repository.StoresGHIRepository;
import com.njevcs.pvawnings.repository.StoresRepository;
import com.njevcs.pvawnings.utils.Constants;

/**
 * @author patel
 *
 *         Nov 19, 2024
 */
@Service
public class NRELService {

    private static final Logger LOGGER = LogManager.getLogger(NRELService.class);

    @Autowired
    private WebClientConfig webClient;

    @Autowired
    private StoresRepository storesRepository;

    @Autowired
    private StoresGHIRepository storesGHIRepository;

    public SolarResourceResponse getSolarFluxResource(String lat, String lon, String address) {
        SolarResourceResponse response = new SolarResourceResponse();

        if (Objects.nonNull(address)) {
            String errorMessage = Constants.NO_DATA + " of Solar Flux for address : " + address;
            ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);
            response = webClient.webClient().get().uri(uriBuilder -> uriBuilder.path("").queryParam("address", address).build()).retrieve()
                    .bodyToMono(SolarResourceResponse.class).block();
        } else if (Objects.nonNull(lat) && Objects.nonNull(lon)) {
            String errorMessage = Constants.NO_DATA + " of Solar Flux for lat : " + lat + ", lon : " + lon;
            ThreadContext.put(Constants.ERROR_MESSAGE, errorMessage);
            response = webClient.webClient().get().uri(uriBuilder -> uriBuilder.path("").queryParam("lat", lat).queryParam("lon", lon).build())
                    .retrieve().bodyToMono(SolarResourceResponse.class).block();
        } else {
            String errorMsg = "Both lat and lon must be specified, or an address must be specified to retrieve the solar flux result.";
            response.setErrorMessage(errorMsg);
        }

        return response;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public boolean insertUpdateStoresGHI(boolean isUpdate, boolean isDeleteAll) {

        if (isDeleteAll) {
            storesGHIRepository.deleteAllGHIs();
            storesGHIRepository.flush();
        }

        List<Stores> allStores;

        if (isUpdate) {
            allStores = storesRepository.findAll();
        } else {
            allStores = storesGHIRepository.findStoresWithoutGHI();
        }
        LOGGER.info("Stores fetched {}", allStores.size());

        allStores.forEach(store -> {
            try {
                String address = store.getAddress() + Constants.COMMA + Constants.SPACE + store.getZipCode().getCity() + Constants.SPACE
                        + store.getZipCode().getZipCode() + Constants.SPACE + Constants.STATE_NJ;
                SolarResourceResponse response = getSolarFluxResource(null, null, address);

                if (CollectionUtils.isEmpty(response.getErrors())) {
                    StoresGHI storesGHI;
                    if (isUpdate) {
                        storesGHI = storesGHIRepository.findByStoresId(store.getId());
                        if (Objects.isNull(storesGHI)) {
                            storesGHI = new StoresGHI();
                            storesGHI.setStore(store);
                        }
                    } else {
                        storesGHI = new StoresGHI();
                        storesGHI.setStore(store);
                    }

                    storesGHI.setJan(response.getOutputs().getAvg_ghi().getMonthly().getJan());
                    storesGHI.setFeb(response.getOutputs().getAvg_ghi().getMonthly().getFeb());
                    storesGHI.setMar(response.getOutputs().getAvg_ghi().getMonthly().getMar());
                    storesGHI.setApr(response.getOutputs().getAvg_ghi().getMonthly().getApr());
                    storesGHI.setMay(response.getOutputs().getAvg_ghi().getMonthly().getMay());
                    storesGHI.setJun(response.getOutputs().getAvg_ghi().getMonthly().getJun());
                    storesGHI.setJul(response.getOutputs().getAvg_ghi().getMonthly().getJul());
                    storesGHI.setAug(response.getOutputs().getAvg_ghi().getMonthly().getAug());
                    storesGHI.setSep(response.getOutputs().getAvg_ghi().getMonthly().getSep());
                    storesGHI.setOct(response.getOutputs().getAvg_ghi().getMonthly().getOct());
                    storesGHI.setNov(response.getOutputs().getAvg_ghi().getMonthly().getNov());
                    storesGHI.setDec(response.getOutputs().getAvg_ghi().getMonthly().getDec());
                    storesGHI.setAnnual(response.getOutputs().getAvg_ghi().getAnnual());
                    store.setStoresGHI(storesGHI);
                    storesGHIRepository.saveAndFlush(storesGHI);
                    LOGGER.info("GHI stored for store #{}", store.getId());
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        });

        LOGGER.info("Stored into database.");

        return true;
    }

}
