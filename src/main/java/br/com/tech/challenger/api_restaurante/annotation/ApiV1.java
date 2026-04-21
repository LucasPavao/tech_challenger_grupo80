package br.com.tech.challenger.api_restaurante.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
@RequestMapping("/v1")
public @interface ApiV1 {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] value() default {};
}
