package org.kshrd.homework002.utils;

import org.kshrd.homework002.model.dto.response.domain.Response;
import org.springframework.http.HttpStatus;

import static java.time.LocalDateTime.now;

public class RequestUtils {

    public static <T> Response<T> getResponse(String message, HttpStatus status, T payload) {
        return new Response<>(
                message,
                status.name(),
                payload,
                now()
        );
    }
}
