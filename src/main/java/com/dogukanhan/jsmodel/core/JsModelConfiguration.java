package com.dogukanhan.jsmodel.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.dialect.AbstractProcessorDialect;

@Configuration
public class JsModelConfiguration {

    @Bean
    public AbstractProcessorDialect jsModelDialect() {
        return new JSModelDialect();
    }

}
