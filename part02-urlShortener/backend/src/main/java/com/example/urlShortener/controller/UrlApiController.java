package com.example.urlShortener.controller;

import com.example.urlShortener.dto.ResponseDto;
import com.example.urlShortener.dto.UrlAnalyticsResponse;
import com.example.urlShortener.dto.UrlShortenRequest;
import com.example.urlShortener.dto.UrlShortenResponse;
import com.example.urlShortener.service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/urls")
@RequiredArgsConstructor
@Tag(name = "URL Management API", description = "APIs for shortening URLs and viewing analytics")
public class UrlApiController {

    private final UrlService urlService;

    @Operation(summary = "Shorten a URL", description = "Creates a new short URL for a given original URL.")
    @PostMapping
    public ResponseEntity<ResponseDto<UrlShortenResponse>> shortenUrl(@RequestBody UrlShortenRequest request) {
        UrlShortenResponse responseData = urlService.shortenUrl(request.originalUrl());
        return ResponseEntity.ok(ResponseDto.success(responseData));
    }

    @Operation(summary = "Get URL Analytics", description = "Retrieves analytics data for a given short key.")
    @GetMapping("/{shortKey}")
    public ResponseEntity<ResponseDto<UrlAnalyticsResponse>> getUrlAnalytics(@PathVariable String shortKey) {
        UrlAnalyticsResponse responseData = urlService.getAnalytics(shortKey);
        return ResponseEntity.ok(ResponseDto.success(responseData));
    }
}