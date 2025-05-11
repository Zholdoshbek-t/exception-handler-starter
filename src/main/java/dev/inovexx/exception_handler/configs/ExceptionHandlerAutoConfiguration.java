package dev.inovexx.exception_handler.configs;

import dev.inovexx.exception_handler.handlers.ExceptionHandlerAdvice;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnProperty(prefix = "exception-handler", name = "enabled", havingValue = "true")
@EnableConfigurationProperties(ExceptionHandlerProperties.class)
public class ExceptionHandlerAutoConfiguration {

    @Bean
    public ExceptionHandlerAdvice exceptionHandlerAdvice() {
        return new ExceptionHandlerAdvice();
    }
}
