package com.rpsouza.security

import com.rpsouza.providers.JWTProvider
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

@Component
class SecurityFilter : OncePerRequestFilter() {

  @Autowired
  private lateinit var jwtProvider: JWTProvider

  @Throws(ServletException::class, IOException::class)
  override fun doFilterInternal(
    request: HttpServletRequest,
    response: HttpServletResponse,
    filterChain: FilterChain
  ) {

    val header: String? = request.getHeader("Authorization")

    if (request.requestURI.startsWith("/company")) {
      if (header != null) {
        val token = jwtProvider.validateToken(header)

        if (token == null || token.subject == null) {
          response.status = HttpServletResponse.SC_UNAUTHORIZED
          return
        }

        request.setAttribute("company_id", token.subject)

        val roles = token.claims["roles"]?.asList(String::class.java)
        val authorities = roles?.map { role ->
          SimpleGrantedAuthority("ROLE_${role}")
        }

        println(authorities)

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