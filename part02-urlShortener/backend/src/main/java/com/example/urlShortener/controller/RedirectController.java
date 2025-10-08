package com.example.urlShortener.controller;

import com.example.urlShortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@Tag(name = "URL Management API", description = "APIs for shortening URLs and viewing analytics")
public class RedirectController {
    private final UrlService urlService;

    @GetMapping("/{shortKey}")
    public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String shortKey) {
        String originalUrl = urlService.getOriginalUrl(shortKey);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(originalUrl));

        // Return a 302 Found status to trigger the browser redirection.
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}