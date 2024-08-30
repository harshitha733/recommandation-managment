package com.epam.recommendation.management.application.dto;

import com.epam.recommendation.management.application.entity.State;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DestinationRequest {

    @NotBlank(message = "Destination name is required.")
    @Size(max = 20, message = "Destination name should not exceed 100 characters.")
    private String destinationName;

    @Size(max = 500, message = "Description should not exceed 500 characters.")
    private String description;

    private String imageUrl;

    private Double rating;

    @NotNull(message = "State ID is required.")
    private State state;

}

