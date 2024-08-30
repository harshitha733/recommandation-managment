package com.epam.recommendation.management.application.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Entity
@Table(name = "countries")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Long countryId;

    @Column(unique = true, nullable = false, name = "country_name")
    private String countryName;

    @Column(name = "image_url" ,columnDefinition = "TEXT")
    private String imageUrl;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<State> states;
}
