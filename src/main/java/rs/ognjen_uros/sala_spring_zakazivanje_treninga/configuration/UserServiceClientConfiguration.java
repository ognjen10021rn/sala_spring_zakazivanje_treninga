package rs.ognjen_uros.sala_spring_zakazivanje_treninga.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.io.IOException;
import java.util.Collections;

@Configuration
public class UserServiceClientConfiguration {

    @Bean
    public RestTemplate userServiceRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:8080/api"));
        restTemplate.setInterceptors(Collections.singletonList(new TokenInterceptor()));
        return restTemplate;
    }

    private class TokenInterceptor implements ClientHttpRequestInterceptor {

        @Override
        public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes,
                                            ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
            HttpHeaders headers = httpRequest.getHeaders();
            headers.add("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpZCI6MSwicm9sZSI6IlJPTEVfQURNSU4iLCJlbWFpbCI6ImFkbWluQGdtYWlsLmNvbSJ9.vXa85a1bQXs7DWdYjyDedSo3iKFwIBuIMDV3JwqB-iol7wc5iCDQzywzb7Uyev3Te5nVeTmDR8wZyymlDv4xTA");
            return clientHttpRequestExecution.execute(httpRequest, bytes);
        }
    }
}
