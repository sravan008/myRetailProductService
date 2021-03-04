package com.target.myretail.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix ="myretail")
@Data
public class ProductConfiguration {

    private List<String> endpoints;

}
