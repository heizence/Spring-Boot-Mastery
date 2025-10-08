package com.example.urlShortener.service;

import com.example.urlShortener.dto.UrlShortenResponse;
import com.example.urlShortener.entity.Url;
import com.example.urlShortener.exception.BusinessException;
import com.example.urlShortener.repository.UrlRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class) // Use Mockito with JUnit 5
class UrlServiceTest {

    @InjectMocks // Create an instance of UrlService and inject mocks into it.
    private UrlService urlService;

    @Mock // Create a mock instance of UrlRepository.
    private UrlRepository urlRepository;

    // Use @BeforeEach and @AfterEach to log the start and end of each test.
    @BeforeEach
    void setUp() {
        log.info("===== Test Started =====");
    }

    @AfterEach
    void tearDown() {
        log.info("===== Test Finished =====");
    }

    // Use @Nested to group related tests.
    @Nested
    @DisplayName("URL 단축 기능 테스트")
    class ShortenUrlTests {
        @Test
        @DisplayName("성공")
        void shortenUrl_success() {
            // given
            String originalUrl = "https://www.google.com";
            Url url = new Url();
            url.setId(1L);
            url.setOriginalUrl(originalUrl);
            log.info(">> Given: originalUrl = {}", originalUrl);

            when(urlRepository.save(any(Url.class))).thenReturn(url);

            // when
            log.info(">> When: Calling shortenUrl method.");
            UrlShortenResponse response = urlService.shortenUrl(originalUrl);

            // then
            // highlight-start
            log.info(">> Then: Response = {}", response);
            // highlight-end
            assertThat(response.originalUrl()).isEqualTo(originalUrl);
            assertThat(response.shortUrl()).isNotNull();
        }
    }

    @Nested
    @DisplayName("원본 URL 조회 기능 테스트")
    class GetOriginalUrlTests {

        @Test
        @DisplayName("성공 및 클릭 수 증가")
        void getOriginalUrl_success() {
            // given
            String shortKey = "testKey";
            Url url = new Url();
            url.setClickCount(0L);
            url.setOriginalUrl("https://www.google.com");
            log.info(">> Given: A valid short key '{}' exists.", shortKey);
            when(urlRepository.findByShortKey(shortKey)).thenReturn(Optional.of(url));

            // when
            log.info(">> When: Calling getOriginalUrl with key: {}", shortKey);
            String resultUrl = urlService.getOriginalUrl(shortKey);

            // then
            // highlight-start
            log.info(">> Then: Returned URL = '{}', Click Count = {}", resultUrl, url.getClickCount());
            // highlight-end
            assertThat(resultUrl).isEqualTo("https://www.google.com");
            assertThat(url.getClickCount()).isEqualTo(1L);
        }

        @Test
        @DisplayName("실패 - 존재하지 않는 키")
        void getOriginalUrl_fail_notFound() {
            // given
            String nonExistentKey = "nonExistent";
            log.info(">> Given: A non-existent short key: {}", nonExistentKey);
            when(urlRepository.findByShortKey(nonExistentKey)).thenReturn(Optional.empty());

            // when & then
            log.info(">> When & Then: Expecting BusinessException for key: {}", nonExistentKey);
            assertThrows(BusinessException.class, () -> {
                urlService.getOriginalUrl(nonExistentKey);
            });
        }
    }
}