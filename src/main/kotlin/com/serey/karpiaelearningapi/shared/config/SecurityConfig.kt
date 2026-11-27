package com.serey.karpiaelearningapi.shared.config

import com.serey.karpiaelearningapi.shared.Appconstants
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig{

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers(
                    HttpMethod.POST,
                    Appconstants.MAIN_PATH+"/auth/register/student",
                    Appconstants.MAIN_PATH+"/auth/login/admin",
                    Appconstants.MAIN_PATH+"/auth/login/student",
                ).permitAll()
                    it.anyRequest().authenticated()
            }
        return http.build()
    }
}