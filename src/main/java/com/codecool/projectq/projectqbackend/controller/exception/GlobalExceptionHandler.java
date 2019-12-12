package com.codecool.projectq.projectqbackend.controller.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;


@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Provides handling for exceptions throughout this service.
     *
     * @param ex      The target exception
     * @param request The current request
     */
    @ExceptionHandler({InvalidRequestDataException.class})
    @Nullable
    public final ResponseEntity<String> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        if (ex instanceof InvalidRequestDataException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            InvalidRequestDataException ie = (InvalidRequestDataException) ex;
            return handleInvalidRequestDataException(ie, headers, status, request);
        } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, null, headers, status, request);
        }
    }

    /**
     * Customize the response for InvalidRequestDataException.
     *
     * @param ex The exception
     * @param headers The headers to be written to the response
     * @param status The selected response status
     * @return a {@code ResponseEntity} instance
     */
    private ResponseEntity<String> handleInvalidRequestDataException(InvalidRequestDataException ex,
                                                                     HttpHeaders headers,
                                                                     HttpStatus status,
                                                                     WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), headers, status, request);
    }

    /**
     * A single place to customize the response body of all Exception types.
     *
     * <p>The default implementation sets the {@link WebUtils#ERROR_EXCEPTION_ATTRIBUTE}
     * request attribute and creates a {@link ResponseEntity} from the given
     * body, headers, and status.
     *
     * @param ex      The exception
     * @param body    The body for the response
     * @param headers The headers for the response
     * @param status  The response status
     * @param request The current request
     */
    protected ResponseEntity<String> handleExceptionInternal(Exception ex, @Nullable String body,
                                                             HttpHeaders headers, HttpStatus status,
                                                             WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }
}
