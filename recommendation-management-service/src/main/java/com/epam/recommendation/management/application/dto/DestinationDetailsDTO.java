package com.epam.recommendation.management.application.dto;

import com.epam.recommendation.management.application.entity.Destination;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DestinationDetailsDTO {
    private Long destinationId;
    private String destinationName;
    private Double rating;
    private String Description;
    private String imageUrl;

    public DestinationDetailsDTO(Destination destination) {
        this.destinationId = destination.getDestinationId();
        this.destinationName = destination.getDestinationName();
        this.rating = destination.getRating();
        this.Description = destination.getDescription();
        this.imageUrl = destination.getImageUrl();
    }
}