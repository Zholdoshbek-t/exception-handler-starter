package dev.inovexx.exception_handler.models;

import org.springframework.http.HttpStatus;

/**
 * Базовое исключение для обработки в приложении.
 * Предоставляет дополнительное поле для HTTP статуса.
 */
public class BaseException extends RuntimeException {

    private final HttpStatus status;

    /**
     * Создает новое исключение BaseException с указанным сообщением и HTTP статусом.
     *
     * @param message Сообщение об ошибке.
     * @param status  HTTP статус, связанный с исключением.
     */
    public BaseException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    /**
     * Создает новое исключение BaseException с указанным сообщением и стандартным HTTP статусом INTERNAL_SERVER_ERROR.
     *
     * @param message Сообщение об ошибке.
     */
    public BaseException(String message) {
        super(message);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * Возвращает HTTP статус, связанный с этим исключением.
     *
     * @return HTTP статус.
     */
    public HttpStatus getStatus() {
        return status;
    }
}
