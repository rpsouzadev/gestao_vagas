package com.rpsouza.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig {

  @Autowired
  private lateinit var securityFilter: SecurityFilter

  @Autowired
  private lateinit var securityCandidateFilter: SecurityCandidateFilter

  @Bean
  fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
    http
      .csrf { csrf -> csrf.disable() }
      .authorizeHttpRequests { auth ->
        auth.requestMatchers(
          "/candidate/",
          "/candidate/auth",
          "/company/",
          "/company/auth",
          "/swagger-ui/**",
          "/v3/api-docs/**",
          "/swagger-resource/**"
        ).permitAll()

        auth.anyRequest().authenticated()
      }
      .addFilterBefore(securityCandidateFilter, BasicAuthenticationFilter::class.java)
      .addFilterBefore(securityFilter, BasicAuthenticationFilter::class.java)

    return http.build()
  }

  @Bean
  fun passwordEncoder(): PasswordEncoder {
    return BCryptPasswordEncoder()
  }
}