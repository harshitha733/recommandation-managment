package com.epam.recommendation.management.application.dto;

public class CountryDto {
    private Long countryId;
    private String countryName;
    private String countryImage;

    public CountryDto(Long countryId, String countryName, String countryImage) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.countryImage = countryImage;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryImage() {
        return countryImage;
    }

    public void setCountryImage(String countryImage) {
        this.countryImage = countryImage;
    }
}
