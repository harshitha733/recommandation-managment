package com.epam.recommendation.management.application.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StateDto {
    private Long stateId;
    private String stateName;
    private String imageUrl;


}

