package com.example.urlShortener.controller;

import com.example.urlShortener.dto.UrlShortenRequest;
import com.example.urlShortener.dto.UrlShortenResponse;
import com.example.urlShortener.exception.BusinessException;
import com.example.urlShortener.exception.ErrorCode;
import com.example.urlShortener.service.UrlService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@WebMvcTest(UrlApiController.class) // Test only the web layer (UrlApiController).
@DisplayName("UrlApiController 테스트")
class UrlApiControllerTest {

    @Autowired
    private MockMvc mockMvc; // For sending mock HTTP requests.

    @Autowired
    private ObjectMapper objectMapper; // For converting objects to JSON.

    @MockitoBean // Create a mock bean for UrlService, as we are not testing its logic here.
    private UrlService urlService;

    @Nested
    @DisplayName("URL 단축 API")
    class ShortenUrlApi {

        @Test
        @DisplayName("성공")
        void shortenUrlApi_success() throws Exception {
            // given
            String originalUrl = "https://example.com";
            UrlShortenRequest request = new UrlShortenRequest(originalUrl);
            UrlShortenResponse response = new UrlShortenResponse(originalUrl, "http://localhost:8082/xY1s");
            log.info(">> Given: request body = {}", objectMapper.writeValueAsString(request));

            given(urlService.shortenUrl(anyString())).willReturn(response);

            // when & then
            log.info(">> When & Then: Perform POST /api/v1/urls and expect 200 OK");
            mockMvc.perform(post("/api/v1/urls")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(200))
                    .andExpect(jsonPath("$.data.originalUrl").value(originalUrl))
                    .andExpect(jsonPath("$.data.shortUrl").value("http://localhost:8082/xY1s"))
                    .andDo(print()); // Print request and response details
        }
    }

    @Nested
    @DisplayName("URL 분석 API")
    class GetAnalyticsApi {

        @Test
        @DisplayName("실패 - 404 Not Found")
        void getAnalyticsApi_fail_notFound() throws Exception {
            // given
            String nonExistentKey = "nonExistent";
            log.info(">> Given: non-existent short key = {}", nonExistentKey);
            given(urlService.getAnalytics(nonExistentKey))
                    .willThrow(new BusinessException(ErrorCode.URL_NOT_FOUND));

            // when & then
            log.info(">> When & Then: Perform GET /api/v1/urls/{} and expect 404 Not Found", nonExistentKey);
            mockMvc.perform(get("/api/v1/urls/{shortKey}", nonExistentKey))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.code").value(404))
                    .andDo(print());
        }
    }
}