package com.example.urlShortener.service;

import com.example.urlShortener.dto.UrlAnalyticsResponse;
import com.example.urlShortener.dto.UrlShortenResponse;
import com.example.urlShortener.entity.Url;
import com.example.urlShortener.exception.BusinessException;
import com.example.urlShortener.exception.ErrorCode;
import com.example.urlShortener.repository.UrlRepository;
import com.example.urlShortener.util.Base62Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UrlService {

    private final UrlRepository urlRepository;
    private final String BASE_URL = "http://localhost:8082/";

    /**
     * Creates a short URL for the given original URL.
     * @param originalUrl The original long URL.
     * @return A DTO containing the original and the new short URL.
     */
    @Transactional
    public UrlShortenResponse shortenUrl(String originalUrl) {
        // 1. Save the original URL to the database to get an ID.
        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        Url savedUrl = urlRepository.save(url);

        // 2. Encode the database ID using Base62.
        String shortKey = Base62Util.encode(savedUrl.getId());

        // 3. Update the entity with the generated short key.
        savedUrl.setShortKey(shortKey);

        // The update will be handled by dirty checking at the end of the transaction.
        return new UrlShortenResponse(originalUrl, BASE_URL + shortKey);
    }

    /**
     * Retrieves the original URL for a given short key and increments the click count.
     * @param shortKey The short key to look up.
     * @return The original URL.
     */
    @Transactional
    public String getOriginalUrl(String shortKey) {
        // Find the URL by shortKey or throw an exception.
        Url url = urlRepository.findByShortKey(shortKey)
                .orElseThrow(() -> new BusinessException(ErrorCode.URL_NOT_FOUND));

        // Increment the click count.
        url.setClickCount(url.getClickCount() + 1);

        return url.getOriginalUrl();
    }

    /**
     * Retrieves analytics for a given short key.
     * @param shortKey The short key to look up.
     * @return A DTO containing analytics data.
     */
    public UrlAnalyticsResponse getAnalytics(String shortKey) {
        Url url = urlRepository.findByShortKey(shortKey)
                .orElseThrow(() -> new BusinessException(ErrorCode.URL_NOT_FOUND));

        return new UrlAnalyticsResponse(url.getOriginalUrl(), BASE_URL + shortKey, url.getClickCount());
    }
}