package br.com.tech.challenger.api_restaurante.config;

import br.com.tech.challenger.api_restaurante.annotation.ApiV1;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("/v1", c -> c.isAnnotationPresent(ApiV1.class));
    }
}
