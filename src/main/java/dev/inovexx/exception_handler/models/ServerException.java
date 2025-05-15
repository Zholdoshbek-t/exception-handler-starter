package dev.inovexx.exception_handler.models;

import org.springframework.http.HttpStatus;

/**
 * Базовое серверное исключение, которое содержит информацию для логирования.
 * Принимает также HTTP статус.
 */
public class ServerException extends RuntimeException {

    private final HttpStatus status;
    private final String log;

    /**
     * Создает новое исключение ServerException с указанным сообщением, HTTP статусом и сообщением для логирования.
     *
     * @param message Сообщение об ошибке.
     * @param status  HTTP статус, связанный с исключением.
     * @param log     Сообщение, предназначенное для записи в лог.
     */
    public ServerException(String message, HttpStatus status, String log) {
        super(message);
        this.status = status;
        this.log = log;
    }

    /**
     * Создает новое исключение ServerException с указанным сообщением и сообщением для логирования,
     * используя стандартный HTTP статус INTERNAL_SERVER_ERROR.
     *
     * @param message Сообщение об ошибке.
     * @param log     Сообщение, предназначенное для записи в лог.
     */
    public ServerException(String message, String log) {
        super(message);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.log = log;
    }

    /**
     * Создает новое исключение ServerException с указанным сообщением, HTTP статусом и использует сообщение
     * также в качестве сообщения для логирования.
     *
     * @param message Сообщение об ошибке и для логирования.
     * @param status  HTTP статус, связанный с исключением.
     */
    public ServerException(String message, HttpStatus status) {
        super(message);
        this.status = status;
        this.log = message;
    }

    /**
     * Создает новое исключение ServerException с указанным сообщением, используя стандартный
     * HTTP статус INTERNAL_SERVER_ERROR и сообщение также в качестве сообщения для логирования.
     *
     * @param message Сообщение об ошибке и для логирования.
     */
    public ServerException(String message) {
        super(message);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.log = message;
    }

    /**
     * Возвращает сообщение, предназначенное для записи в лог.
     *
     * @return Сообщение для логирования.
     */
    public String getLog() {
        return log;
    }

    /**
     * Возвращает сообщение, предназначенное для статуса HTTP.
     *
     * @return Статус HTTP.
     */
    public HttpStatus getStatus() {
        return status;
    }
}
