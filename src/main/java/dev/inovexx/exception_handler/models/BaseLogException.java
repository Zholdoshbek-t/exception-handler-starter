package dev.inovexx.exception_handler.models;

import org.springframework.http.HttpStatus;

/**
 * Базовое исключение, которое также содержит информацию для логирования.
 * Наследуется от BaseException и добавляет поле 'log'.
 */
public class BaseLogException extends BaseException {

    private final String log;

    /**
     * Создает новое исключение BaseLogException с указанным сообщением, HTTP статусом и сообщением для логирования.
     *
     * @param message Сообщение об ошибке.
     * @param status  HTTP статус, связанный с исключением.
     * @param log     Сообщение, предназначенное для записи в лог.
     */
    public BaseLogException(String message, HttpStatus status, String log) {
        super(message, status);
        this.log = log;
    }

    /**
     * Создает новое исключение BaseLogException с указанным сообщением и сообщением для логирования,
     * используя стандартный HTTP статус INTERNAL_SERVER_ERROR.
     *
     * @param message Сообщение об ошибке.
     * @param log     Сообщение, предназначенное для записи в лог.
     */
    public BaseLogException(String message, String log) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
        this.log = log;
    }

    /**
     * Создает новое исключение BaseLogException с указанным сообщением, HTTP статусом и использует сообщение
     * также в качестве сообщения для логирования.
     *
     * @param message Сообщение об ошибке и для логирования.
     * @param status  HTTP статус, связанный с исключением.
     */
    public BaseLogException(String message, HttpStatus status) {
        super(message, status);
        this.log = message;
    }

    /**
     * Создает новое исключение BaseLogException с указанным сообщением, используя стандартный
     * HTTP статус INTERNAL_SERVER_ERROR и сообщение также в качестве сообщения для логирования.
     *
     * @param message Сообщение об ошибке и для логирования.
     */
    public BaseLogException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
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
}
