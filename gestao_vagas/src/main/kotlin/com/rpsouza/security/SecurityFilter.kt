package com.rpsouza.security

import com.rpsouza.providers.JWTProvider
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
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
    SecurityContextHolder.getContext().authentication = null

    val header: String? = request.getHeader("Authorization")

    if (header != null) {
      val subjectToken = jwtProvider.validateToken(header)

      if (subjectToken.isEmpty()) {
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        return
      }

      request.setAttribute("company_id", subjectToken)
      val authToken = UsernamePasswordAuthenticationToken(subjectToken, null, emptyList())
      SecurityContextHolder.getContext().authentication = authToken
    }

    filterChain.doFilter(request, response)
  }
}