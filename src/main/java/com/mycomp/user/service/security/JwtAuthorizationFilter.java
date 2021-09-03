package com.mycomp.user.service.security;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;


import com.auth0.jwt.JWT;
import com.mycomp.user.service.data.model.UserCredential;
import com.mycomp.user.service.data.repository.UserCredentialRepository;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
  private UserCredentialRepository userRepository;

  public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserCredentialRepository userRepository) {
    super(authenticationManager);
    this.userRepository = userRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
    // Read the Authorization header, where the JWT token should be
    String header = request.getHeader(JwtProperties.HEADER_STRING);

    // If header does not contain BEARER or is null delegate to Spring impl and exit
    if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
      chain.doFilter(request, response);
      return;
    }

    // If header is present, try grab user principal from database and perform authorization
    Authentication authentication = getUsernamePasswordAuthentication(request);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // Continue filter execution
    chain.doFilter(request, response);
  }

  private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
    String token = request.getHeader(JwtProperties.HEADER_STRING)
        .replace(JwtProperties.TOKEN_PREFIX,"");

    if (token != null) {
      // parse the token and validate it
      String userName = JWT.require(HMAC512(JwtProperties.SECRET.getBytes()))
          .build()
          .verify(token)
          .getSubject();

      if (userName != null) {
        UserCredential user = userRepository.findByUsername(userName);
        UserPrincipal principal = new UserPrincipal(user);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userName, null, Arrays.asList());
        return auth;
      }
      return null;
    }
    return null;
  }
}
