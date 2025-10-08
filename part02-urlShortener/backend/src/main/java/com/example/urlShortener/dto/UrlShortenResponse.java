package com.example.urlShortener.dto;

import io.swagger.v3.oas.annotations.media.Schema;

// A record for the URL shortening response payload.
@Schema(description = "Response containing the new short URL")
public record UrlShortenResponse(
        @Schema(description = "The original URL that was provided", example = "https://www.google.com")
        String originalUrl,
        @Schema(description = "The newly generated short URL", example = "http://localhost:8082/3d7")
        String shortUrl
) {}