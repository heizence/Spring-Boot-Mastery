package com.example.urlShortener.dto;

import io.swagger.v3.oas.annotations.media.Schema;

// A record for the URL shortening request payload.
@Schema(description = "Request to shorten a new URL")
public record UrlShortenRequest(
        @Schema(description = "The original, long URL to be shortened", example = "https://www.google.com")
        String originalUrl
) {}
