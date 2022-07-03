package com.example.priorlearning.web.config

import com.example.priorlearning.web.handler.AuthHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
class AuthRouter(private val authHandler: AuthHandler) {

    @Bean
    fun routerFunction(): RouterFunction<ServerResponse> = router {
        accept(MediaType.APPLICATION_JSON).nest {
            POST("/login", authHandler::login)
        }
    }
}