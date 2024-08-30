package com.epam.recommendation.management.application.dto;

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

}
