package com.example.priorlearning.auth.port

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono

private const val KAKAO_OAUTH_TOKEN_URL = "https://kauth.kakao.com/oauth/token"
private const val GRANT_TYPE = "authorization_code"
private const val CLIENT_ID = "clientId"
private const val REDIRECT_URI = "http://localhost:8080/login"

@Component
class KakaoAccessTokenLoadAdapter : KakaoAccessTokenLoadPort {
    override fun findKakaoAccessToken(code: String): Mono<KakaoAccessTokenLoadPort.Response> {
        val baseUrl = UriComponentsBuilder.fromHttpUrl(KAKAO_OAUTH_TOKEN_URL)
            .queryParam("grant_type", GRANT_TYPE)
            .queryParam("client_id", CLIENT_ID)
            .queryParam("redirect_uri", REDIRECT_URI)
            .queryParam("code", code)
            .toUriString()

        return WebClient.builder().baseUrl(baseUrl).build()
            .get()
            .retrieve()
            .bodyToMono(KakaoAccessTokenLoadPort.Response::class.java)
    }
}