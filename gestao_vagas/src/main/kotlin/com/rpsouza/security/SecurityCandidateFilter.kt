package com.rpsouza.security

import com.rpsouza.providers.JWTCandidateProvider
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class SecurityCandidateFilter : OncePerRequestFilter() {

  @Autowired
  private lateinit var jwtCandidateProvider: JWTCandidateProvider


  override fun doFilterInternal(
    request: HttpServletRequest,
    response: HttpServletResponse,
    filterChain: FilterChain
  ) {

    val header: String? = request.getHeader("Authorization")

    if (request.requestURI.startsWith("/candidate")) {
      if (header != null) {
        val token = jwtCandidateProvider.validateToken(header)

        if (token == null || token.subject == null) {
          response.status = HttpServletResponse.SC_UNAUTHORIZED
          return
        }

        request.setAttribute("candidate_id", token.subject)


        val roles = token.claims["roles"]?.asList(String::class.java)

        val authorities = roles?.map { role ->
          SimpleGrantedAuthority("ROLE_${role}")
        }

        val authToken = UsernamePasswordAuthenticationToken(
          token.subject,
          null,
          authorities
        )


        SecurityContextHolder.getContext().authentication = authToken
      }

    }

    filterChain.doFilter(request, response)
  }
}