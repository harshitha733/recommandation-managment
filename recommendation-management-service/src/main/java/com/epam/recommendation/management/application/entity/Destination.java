package com.epam.recommendation.management.application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "destinations")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "destination_id")
    private Long destinationId;

    @Column(nullable = false, unique = true,name = "destination_name")
    private String destinationName;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url" ,columnDefinition = "TEXT")
    private String imageUrl;

    @Column(name = "rating")
    private Double rating;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private State state;
}
