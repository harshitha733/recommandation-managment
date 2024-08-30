package com.epam.recommendation.management.application.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record ApiResponse<T>(
        HttpStatus status,
        String message,
        T data
) {}

