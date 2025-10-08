package com.example.urlShortener.repository;

import com.example.urlShortener.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// By extending JpaRepository, we get standard CRUD methods for the Url entity.
public interface UrlRepository extends JpaRepository<Url, Long> {

    /**
     * Finds a Url entity by its shortKey.
     * Spring Data JPA automatically implements this method based on its name.
     * @param shortKey The short key to search for.
     * @return An Optional containing the found Url entity, or an empty Optional if not found.
     */
    Optional<Url> findByShortKey(String shortKey);
}