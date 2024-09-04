package com.epam.recommendation.management.application.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CountryDto {
    private Long countryId;
    private String countryName;
    private String countryImage;
}
