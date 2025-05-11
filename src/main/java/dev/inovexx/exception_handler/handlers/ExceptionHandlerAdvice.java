package dev.inovexx.exception_handler.handlers;

import dev.inovexx.exception_handler.models.BaseException;
import dev.inovexx.exception_handler.models.BaseLogException;
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
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiResponse> handleThrowable(Throwable e) {
        Throwable targetException = e.getMessage() == null && e.getCause() != null ? e.getCause() : e;
        log.error(e.getMessage(), targetException);
        String message = (e.getMessage() == null || e.getMessage().isEmpty()) ? "Неизвестная ошибка" : e.getMessage();
        return new ResponseEntity<>(new ApiResponse(message), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Обрабатывает исключения типа BaseException.
     * Возвращает ответ с сообщением об ошибке и HTTP статусом, определенным в исключении.
     *
     * @param e Возникшее исключение BaseException.
     * @return ResponseEntity с ApiResponse, содержащим сообщение об ошибке и HTTP статус из исключения.
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse> handleThrowable(BaseException e) {
        return buildResponse(e);
    }

    /**
     * Обрабатывает исключения типа BaseLogException.
     * Записывает сообщение для логирования из исключения и возвращает ответ, используя buildResponse.
     *
     * @param e Возникшее исключение BaseLogException.
     * @return ResponseEntity с ApiResponse, содержащим сообщение об ошибке и HTTP статус из исключения.
     */
    @ExceptionHandler(BaseLogException.class)
    public ResponseEntity<ApiResponse> handleThrowable(BaseLogException e) {
        log.error(e.getLog());
        return buildResponse(e);
    }

    /**
     * Вспомогательный метод для построения ResponseEntity на основе BaseException.
     *
     * @param e Исключение BaseException, содержащее сообщение и HTTP статус.
     * @return ResponseEntity с ApiResponse и HTTP статусом из исключения.
     */
    private ResponseEntity<ApiResponse> buildResponse(BaseException e) {
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