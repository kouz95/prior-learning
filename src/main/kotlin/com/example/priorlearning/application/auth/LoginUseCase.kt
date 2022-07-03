package com.example.priorlearning.application.auth

import reactor.core.publisher.Mono

interface LoginUseCase {
    fun login(loginCommand: LoginCommand): Mono<LoginResult>
}
