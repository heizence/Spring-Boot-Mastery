package com.example.urlShortener.controller;

import com.example.urlShortener.controller.RedirectController;
import com.example.urlShortener.service.UrlService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WebMvcTest(RedirectController.class)
@DisplayName("RedirectController 테스트")
class RedirectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UrlService urlService;

    @Test
    @DisplayName("리다이렉션 성공")
    void redirect_success() throws Exception {
        // given
        String shortKey = "xY1s";
        String originalUrl = "https://example.com";
        log.info(">> Given: shortKey = {}, originalUrl = {}", shortKey, originalUrl);
        given(urlService.getOriginalUrl(shortKey)).willReturn(originalUrl);

        // when & then
        log.info(">> When & Then: Perform GET /{} and expect 302 Found", shortKey);
        mockMvc.perform(get("/{shortKey}", shortKey))
                .andExpect(status().isFound()) // Check for 302 Found status
                .andExpect(redirectedUrl(originalUrl)); // Check the 'Location' header
    }
}