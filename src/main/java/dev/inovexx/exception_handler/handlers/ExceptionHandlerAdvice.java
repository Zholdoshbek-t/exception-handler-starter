package dev.inovexx.exception_handler.handlers;

import dev.inovexx.exception_handler.models.BaseException;
import dev.inovexx.exception_handler.models.BaseLogException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@Order(200)
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiResponse> handleThrowable(Throwable e) {
        Throwable targetException = e.getMessage() == null && e.getCause() != null ? e.getCause() : e;
        log.error(e.getMessage(), targetException);
        String message = (e.getMessage() == null || e.getMessage().isEmpty()) ? "Неизвестная ошибка" : e.getMessage();
        return new ResponseEntity<>(new ApiResponse(message), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse> handleThrowable(BaseException e) {
        return buildResponse(e);
    }

    @ExceptionHandler(BaseLogException.class)
    public ResponseEntity<ApiResponse> handleThrowable(BaseLogException e) {
        log.error(e.getLog());
        return buildResponse(e);
    }

    private ResponseEntity<ApiResponse> buildResponse(BaseException e) {
        return new ResponseEntity<>(new ApiResponse(e.getMessage()), e.getStatus());
    }

    public record ApiResponse(String message) {
    }
}
