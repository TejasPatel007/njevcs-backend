/**
 * 
 */
package com.njevcs.pvawnings;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UriComponentsBuilder;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

/**
 * @author patel
 *
 *         Nov 19, 2024
 */
@Configuration
public class WebClientConfig {

    private static final Logger LOGGER = LogManager.getLogger(WebClientConfig.class);

    private static final String NREL_BASE_URL = "";
    private static final String NREL_API_KEY = "";

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOriginPatterns("*").allowedMethods("GET", "POST", "PUT", "DELETE").allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

    @Bean
    public WebClient webClient() {
        HttpClient httpClient = HttpClient.create().secure().responseTimeout(Duration.ofSeconds(5))
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5 * 1000)
                .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(5, TimeUnit.SECONDS)).addHandlerLast(new WriteTimeoutHandler(5)));

        ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

        return WebClient.builder().baseUrl(NREL_BASE_URL).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(connector)
                .filter(ExchangeFilterFunction.ofRequestProcessor(req -> Mono.just(ClientRequest.from(req)
                        .url(UriComponentsBuilder.fromUri(req.url()).queryParam("api_key", NREL_API_KEY).build(true).toUri()).build())))
                .filter(logRequest()).filter(logResponse()).build();
    }

    // Method to log the request using SLF4J
    private ExchangeFilterFunction logRequest() {
        return (request, next) -> {
            LOGGER.info("Request: {} {}", request.method(), request.url());
            request.headers().forEach((name, values) -> values.forEach(value -> LOGGER.info("{}: {}", name, value)));
            return next.exchange(request);
        };
    }

    // Method to log the response using SLF4J
    private ExchangeFilterFunction logResponse() {
        return (request, next) -> next.exchange(request).doOnNext(response -> LOGGER.info("Response status code: {}", response.statusCode()));
    }

}
