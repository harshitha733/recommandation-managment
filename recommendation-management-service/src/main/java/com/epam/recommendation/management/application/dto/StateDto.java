package com.epam.recommendation.management.application.dto;

import com.epam.recommendation.management.application.entity.Country;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

