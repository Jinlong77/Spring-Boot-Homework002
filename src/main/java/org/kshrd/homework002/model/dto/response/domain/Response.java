package org.kshrd.homework002.model.dto.response.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public record Response<T>(String message, String status, T payload, LocalDateTime time) {
}
