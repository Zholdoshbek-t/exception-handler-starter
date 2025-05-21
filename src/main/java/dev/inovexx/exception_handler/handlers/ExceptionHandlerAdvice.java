package dev.inovexx.exception_handler.handlers;

import dev.inovexx.exception_handler.models.ServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Глобальный обработчик исключений для REST контроллеров.
 * Перехватывает различные типы исключений и возвращает стандартизированные ответы ApiResponse.
 */
@Slf4j
@Order(200)
@ControllerAdvice
public class ExceptionHandlerAdvice {

    /**
     * Обрабатывает все необработанные исключения типа Throwable.
     * Записывает сообщение об ошибке и стек трейс в лог и возвращает ответ с HTTP статусом 500.
     *
     * @param e Возникшее исключение.
     * @return ResponseEntity с ApiResponse, содержащим сообщение об ошибке и HTTP статус 500.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException e) {
        Throwable targetException = e.getMessage() == null && e.getCause() != null ? e.getCause() : e;
        log.error(e.getMessage(), targetException);
        String message = (e.getMessage() == null || e.getMessage().isEmpty()) ? "Неизвестная ошибка" : e.getMessage();
        return new ResponseEntity<>(new ApiResponse(message), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Обрабатывает исключения типа ServerException.
     * Записывает сообщение для логирования из исключения и возвращает ответ, используя buildResponse.
     *
     * @param e Возникшее исключение ServerException.
     * @return ResponseEntity с ApiResponse, содержащим сообщение об ошибке и HTTP статус из исключения.
     */
    @ExceptionHandler(ServerException.class)
    public ResponseEntity<ApiResponse> handleThrowable(ServerException e) {
        log.error(e.getLog());
        return buildResponse(e);
    }

    /**
     * Вспомогательный метод для построения ResponseEntity на основе BaseException.
     *
     * @param e Исключение ServerException, содержащее сообщение и HTTP статус.
     * @return ResponseEntity с ApiResponse и HTTP статусом из исключения.
     */
    private ResponseEntity<ApiResponse> buildResponse(ServerException e) {
        return new ResponseEntity<>(new ApiResponse(e.getMessage()), e.getStatus());
    }

    /**
     * Запись (record) для представления стандартизированного тела ответа API об ошибке.
     * Содержит только сообщение об ошибке.
     *
     * @param message Сообщение об ошибке.
     */
    public record ApiResponse(String message) {
    }
}