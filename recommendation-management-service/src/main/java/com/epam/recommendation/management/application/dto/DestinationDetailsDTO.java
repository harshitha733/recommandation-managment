package com.epam.recommendation.management.application.dto;

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

}
