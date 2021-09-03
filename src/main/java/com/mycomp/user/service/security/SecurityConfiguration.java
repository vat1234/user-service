package com.mycomp.user.service.security;

import com.mycomp.user.service.data.model.UserCredential;
import com.mycomp.user.service.data.repository.UserCredentialRepository;
import com.mycomp.user.service.data.repository.UserDetailsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter  {

  private UserPrincipalDetailsService userPrincipalDetailsService;
  private UserCredentialRepository userRepository;
  private UserDetailsRepository userDetailsRepository;
  private PasswordEncoder passwordEncoder;

  public SecurityConfiguration(UserPrincipalDetailsService userPrincipalDetailsService, UserCredentialRepository userRepository,
                               UserDetailsRepository userDetailsRepository,
                               PasswordEncoder passwordEncoder) {
    this.userPrincipalDetailsService = userPrincipalDetailsService;
    this.userRepository = userRepository;
    this.userDetailsRepository = userDetailsRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(authenticationProvider());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        // remove csrf and state in session because in jwt we do not need them
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        // add jwt filters (1. authentication, 2. authorization)
        .addFilter(new JwtAuthenticationFilter(authenticationManager(),this.userDetailsRepository))
        .addFilter(new JwtAuthorizationFilter(authenticationManager(),  this.userRepository))
        .authorizeRequests()
        // configure access rules
        .antMatchers(HttpMethod.POST, "/login").permitAll()
        .antMatchers("/v1/users").permitAll()
        .anyRequest().authenticated();
  }

  @Bean
  DaoAuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
    daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);

    return daoAuthenticationProvider;
  }

}
