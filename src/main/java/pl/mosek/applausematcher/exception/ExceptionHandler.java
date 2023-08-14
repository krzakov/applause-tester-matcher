package pl.mosek.applausematcher.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static pl.mosek.applausematcher.exception.ExceptionUtils.*;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    public ApiError handleDeviceDescriptionNotFoundException(NotFoundException ex) {

        var apiError = ApiError.builder()
                .timestamp(Instant.now())
                .correlationId(UUID.randomUUID().toString())
                .httpStatus(NOT_FOUND)
                .httpStatusCode(NOT_FOUND.value())
                .messages(List.of(DEVICE_NAME_NOT_FOUND + ex.getMessage()))
                .build();
        log.error(apiError.toString());

        return apiError;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ApiError handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {

        var apiError = ApiError.builder()
                .timestamp(Instant.now())
                .correlationId(UUID.randomUUID().toString())
                .httpStatus(BAD_REQUEST)
                .httpStatusCode(BAD_REQUEST.value())
                .messages(getMessagesForHttpMessageNotReadableException(ex))
                .apiFieldErrors(getApiFieldErrorsFromHttpMessageNotReadableException(ex))
                .build();

        log.error(apiError.toString());
        return apiError;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ApiError handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        List<String> messages = getMessagesFromMethodArgumentNotValidException(ex);
        messages.add(METHOD_ARGUMENT_NOT_VALID);

        var apiError = ApiError.builder()
                .timestamp(Instant.now())
                .correlationId(UUID.randomUUID().toString())
                .httpStatus(BAD_REQUEST)
                .httpStatusCode(BAD_REQUEST.value())
                .messages(messages)
                .apiFieldErrors(getApiFieldErrorsFromMethodArgumentNotValidException(ex))
                .build();

        log.error(apiError.toString());
        return apiError;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ApiError handleHttpMediaTypeNotSupportedException() {

        var apiError = ApiError.builder()
                .timestamp(Instant.now())
                .correlationId(UUID.randomUUID().toString())
                .httpStatus(BAD_REQUEST)
                .httpStatusCode(BAD_REQUEST.value())
                .messages(List.of(MEDIA_NOT_SUPPORTED))
                .build();
        log.error(apiError.toString());

        return apiError;
    }
}
