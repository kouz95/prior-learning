package com.example.priorlearning.application.auth

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class LoginService: LoginUseCase{
    override fun login(loginCommand: LoginCommand): Mono<LoginResult> {
        TODO("Not yet implemented")
    }
}