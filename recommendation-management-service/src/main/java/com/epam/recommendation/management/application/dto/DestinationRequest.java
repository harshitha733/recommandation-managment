package com.epam.recommendation.management.application.dto;

import com.epam.recommendation.management.application.entity.State;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DestinationRequest {

    @NotBlank(message = "Destination name is required.")
    @Size(max = 20, message = "Destination name should not exceed 100 characters.")
    private String destinationName;

    @NotBlank(message = "Destination name is required.")
    @Size(max = 500, message = "Description should not exceed 500 characters.")
    private String description;

    @NotBlank(message = "Image url is required.")
    private String imageUrl;

    @NotNull(message = "Rating is required.")
    @Min(value = 0, message = "Rating should be at least 0.")
    @Max(value = 5, message = "Rating should be at most 5.")
    private Double rating;

    @NotNull(message = "State ID is required.")
    private State state;

}

