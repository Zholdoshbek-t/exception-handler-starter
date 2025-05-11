package dev.inovexx.exception_handler.configs;

import dev.inovexx.exception_handler.handlers.ExceptionHandlerAdvice;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Автоматическая конфигурация для включения обработки исключений.
 * Конфигурирует {@link ExceptionHandlerAdvice} на основе свойства 'exception-handler.enabled'.
 */
@AutoConfiguration
@ConditionalOnProperty(prefix = "exception-handler", name = "enabled", havingValue = "true")
@EnableConfigurationProperties(ExceptionHandlerProperties.class)
public class ExceptionHandlerAutoConfiguration {

    /**
     * Создает и регистрирует бин {@link ExceptionHandlerAdvice}.
     * Этот бин является глобальным обработчиком исключений для REST контроллеров.
     * Он будет создан только если свойство 'exception-handler.enabled' установлено в 'true'
     * (или не установлено, так как 'matchIfMissing' по умолчанию 'false' здесь).
     *
     * @return Экземпляр {@link ExceptionHandlerAdvice}.
     */
    @Bean
    public ExceptionHandlerAdvice exceptionHandlerAdvice() {
        return new ExceptionHandlerAdvice();
    }
}