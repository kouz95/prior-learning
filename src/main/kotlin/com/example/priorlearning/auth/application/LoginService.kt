package com.example.priorlearning.auth.application

import com.example.priorlearning.auth.port.KakaoAccessTokenLoadPort
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class LoginService(private val kakaoAccessTokenLoadPort: KakaoAccessTokenLoadPort) : LoginUseCase {
    override fun login(code: String): Mono<LoginUseCase.LoginResult> =
        kakaoAccessTokenLoadPort.findKakaoAccessToken(code)
            .doOnNext {
                log.info("accessToken={}", it.access_token)
                log.info("refreshToken={}", it.refresh_token)
            }
            .map { LoginUseCase.LoginResult(it.access_token) }

    companion object {
        private val log = LoggerFactory.getLogger(LoginService::class.java.name)
    }
}