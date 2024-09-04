package com.epam.recommendation.management.application.response;

import lombok.Builder;

@Builder
public record ApiResponse<T>(
        int status,
        String message,
        T data
) {}

