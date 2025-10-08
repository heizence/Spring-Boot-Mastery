package com.example.urlShortener.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "url_mappings") // Specifies the database table name.
@Getter
@Setter
@NoArgsConstructor // Required by JPA.
public class Url {

    @Id // Marks this field as the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures the ID generation strategy.
    private Long id;

    @Column(nullable = false, length = 2048) // A long URL can be quite lengthy.
    private String originalUrl;

    @Column(unique = true) // The short key must be unique.
    private String shortKey;

    @Column(nullable = false)
    private Long clickCount = 0L; // Initialize with a default value of 0.

    @CreationTimestamp // Automatically sets the value to the current timestamp when created.
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}