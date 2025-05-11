package dev.inovexx.exception_handler.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Класс конфигурационных свойств для обработки исключений.
 * Позволяет настраивать поведение обработки исключений через внешние конфигурационные файлы.
 * Префикс для свойств: 'exception-handler'.
 */
@Getter
@Setter
@ConfigurationProperties("exception-handler")
public class ExceptionHandlerProperties {

    private boolean enabled;
}