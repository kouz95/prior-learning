package com.example.priorlearning.web.handler

import com.example.priorlearning.application.auth.LoginCommand
import com.example.priorlearning.application.auth.LoginResult
import com.example.priorlearning.application.auth.LoginUseCase
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class AuthHandler(private val loginUseCase: LoginUseCase) {
    fun login(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(Request::class.java)
            .map(Request::toCommand)
            .flatMap { loginUseCase.login(it) }
            .map { Response.of(it) }
            .flatMap { ServerResponse.ok().body(BodyInserters.fromValue(it)) }

    data class Request(private val id: String) {
        fun toCommand(): LoginCommand = LoginCommand(id)
    }

    data class Response(private val token: String) {
        companion object {
            fun of(result: LoginResult): Response = Response(result.token)
        }
    }
}
