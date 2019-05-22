package com.nik_makrv.multiplier.config;

import com.nik_makrv.multiplier.client.AdderClient;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public AdderClient adderClient() {
        return Feign.builder()
                .decoder(new JacksonDecoder())
                .target(AdderClient.class, "http://adder-service");
    }

}
