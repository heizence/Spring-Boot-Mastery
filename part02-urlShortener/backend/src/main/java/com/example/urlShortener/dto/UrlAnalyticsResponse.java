package com.example.urlShortener.dto;

import io.swagger.v3.oas.annotations.media.Schema;

// A record for the URL analytics response payload.
@Schema(description = "Response containing analytics for a short URL")
public record UrlAnalyticsResponse(
        @Schema(description = "The original URL", example = "https://www.google.com")
        String originalUrl,
        @Schema(description = "The short URL", example = "http://localhost:8082/3d7")
        String shortUrl,
        @Schema(description = "The total number of times the short URL has been accessed", example = "42")
        Long clickCount
) {}