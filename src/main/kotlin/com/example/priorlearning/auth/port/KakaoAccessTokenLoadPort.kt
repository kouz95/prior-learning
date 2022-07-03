package com.example.priorlearning.auth.port

import reactor.core.publisher.Mono

interface KakaoAccessTokenLoadPort {
    fun findKakaoAccessToken(code: String): Mono<Response>

    data class Response(
        val access_token: String,
        val refresh_token: String,
    )
}
