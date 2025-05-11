package dev.inovexx.exception_handler.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("exception-handler")
public class ExceptionHandlerProperties {

    private boolean enabled;
}
