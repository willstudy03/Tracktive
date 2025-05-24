package com.tracktive.apiGateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class JwtValidationGatewayFilterFactory extends AbstractGatewayFilterFactory<JwtValidationGatewayFilterFactory.Config> {

    private static final Logger logger = LoggerFactory.getLogger(JwtValidationGatewayFilterFactory.class);

    private final WebClient webClient;

    public JwtValidationGatewayFilterFactory(WebClient.Builder webClientBuilder, @Value("${auth.service.url}") String authServiceUrl){
        super(Config.class);
        this.webClient = webClientBuilder.baseUrl(authServiceUrl).build();
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("excludePaths");
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String path = exchange.getRequest().getURI().getPath();
            logger.info("JWT Filter processing path: {}", path);

            // Check if the current path matches any excluded paths
            if (config.excludePaths != null) {
                logger.info("Excluded paths: {}", config.excludePaths);
                for (String excludePath : config.excludePaths) {
                    // Remove trailing '**' for matching
                    String pathToMatch = excludePath.endsWith("/**")
                            ? excludePath.substring(0, excludePath.length() - 2)
                            : excludePath;

                    if (path.startsWith(pathToMatch) || path.contains(pathToMatch)) {
                        logger.info("Path {} matches excluded path {}. Skipping JWT validation.", path, excludePath);
                        return chain.filter(exchange);
                    }
                }
            }

            logger.info("Path {} requires JWT validation", path);
            String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            if (token == null || !token.startsWith("Bearer ")){
                logger.warn("No valid Bearer token found for path: {}", path);
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            return webClient.get()
                    .uri("authentication/validate")
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .retrieve()
                    .toBodilessEntity()
                    .then(chain.filter(exchange));
        };
    }

    public static class Config {
        private List<String> excludePaths;

        public List<String> getExcludePaths() {
            return excludePaths;
        }

        public void setExcludePaths(String excludePathsString) {
            if (StringUtils.hasText(excludePathsString)) {
                this.excludePaths = Arrays.asList(excludePathsString.split(","));
            }
        }
    }
}
