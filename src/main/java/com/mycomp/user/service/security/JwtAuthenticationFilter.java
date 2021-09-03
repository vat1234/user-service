package com.mycomp.user.service.security;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;


import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycomp.user.service.data.repository.UserDetailsRepository;
import com.mycomp.user.service.model.Login;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private AuthenticationManager authenticationManager;
  UserDetailsRepository userDetailsRepository;

  public JwtAuthenticationFilter(AuthenticationManager authenticationManager, UserDetailsRepository userDetailsRepository) {
    this.authenticationManager = authenticationManager;
    this.userDetailsRepository = userDetailsRepository;

  }

  /* Trigger when we issue POST request to /login
  We also need to pass in {"username":"dan", "password":"dan123"} in the request body
   */
  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

    // Grab credentials and map them to login viewmodel
    Login credentials = null;
    try {
      credentials = new ObjectMapper().readValue(request.getInputStream(), Login.class);
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Create login token
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
        credentials.getUsername(),
        credentials.getPassword(),
        new ArrayList<>());

    // Authenticate user
    Authentication auth = authenticationManager.authenticate(authenticationToken);

    return auth;
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    // Grab principal
    UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();

    long idByEmail = userDetailsRepository.findByEmail(principal.getUsername()).getId();
    // Create JWT Token
    String token = JWT.create()
        .withSubject(String.valueOf(idByEmail))
        .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
        .sign(HMAC512(JwtProperties.SECRET.getBytes()));

    // Add token in response
    response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX +  token);
  }
}