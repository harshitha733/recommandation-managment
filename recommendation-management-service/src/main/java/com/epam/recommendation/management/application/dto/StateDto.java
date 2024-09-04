package com.epam.recommendation.management.application.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StateDto {
    private Long stateId;
    private String stateName;
    private String imageUrl;
}

