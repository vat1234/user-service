package com.mycomp.user.service.security;

public class JwtProperties {
  public static final String SECRET = "SomeSecretForJWTGeneration";
  public static final int EXPIRATION_TIME = 6000000; // Increased only for testing
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String HEADER_STRING = "Authorization";
}
