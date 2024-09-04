package com.epam.recommendation.management.application.dto;

import com.epam.recommendation.management.application.entity.Destination;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class DestinationListDTO {

    private Long destinationId;
    private String destinationName;
    private String imageUrl;

    public DestinationListDTO(Destination destination) {
        this.destinationId = destination.getDestinationId();
        this.destinationName = destination.getDestinationName();
        this.imageUrl = destination.getImageUrl();
    }
}