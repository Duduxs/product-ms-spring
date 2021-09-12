package com.edudev.productms.exceptions.handler;

import com.edudev.productms.exceptions.BadRequestHttpException;
import com.edudev.productms.exceptions.ConflictHttpException;
import com.edudev.productms.exceptions.NotFoundHttpException;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public final class HttpExceptionHandler {

    @ExceptionHandler(BadRequestHttpException.class)
    public ResponseEntity<Object> handleBadRequestException(final Exception e) {
        var jsonMessageError = new ExceptionJsonMessage(BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(jsonMessageError, BAD_REQUEST);
    }

    @ExceptionHandler(ConflictHttpException.class)
    public ResponseEntity<Object> handleConflictException(final Exception e) {
        var jsonMessageError = new ExceptionJsonMessage(CONFLICT, e.getMessage());
        return new ResponseEntity<>(jsonMessageError, CONFLICT);
    }

    @ExceptionHandler(NotFoundHttpException.class)
    public ResponseEntity<Object> handleNotFoundException(final Exception e) {
        var jsonMessageError = new ExceptionJsonMessage(NOT_FOUND, e.getMessage());
        return new ResponseEntity<>(jsonMessageError, NOT_FOUND);
    }

    private static final class ExceptionJsonMessage {

        @JsonProperty("status_code")
        private final Integer statusCode;
        @JsonProperty("message")
        private final String payload;

        public ExceptionJsonMessage(final HttpStatus status, final String payload) {
            this.statusCode = status.value();
            this.payload = payload;
        }

        public Integer getStatusCode() { return statusCode; }

        public String getPayload() { return payload; }
    }

}
