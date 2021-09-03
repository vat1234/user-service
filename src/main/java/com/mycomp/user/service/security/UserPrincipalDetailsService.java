package com.mycomp.user.service.security;

import com.mycomp.user.service.data.model.UserCredential;
import com.mycomp.user.service.data.repository.UserCredentialRepository;
import com.mycomp.user.service.data.repository.UserDetailsRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
  private UserCredentialRepository userRepository;

  public UserPrincipalDetailsService(UserCredentialRepository userRepository) {
    this.userRepository = userRepository;
  }


  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

    UserCredential user = this.userRepository.findByUsername(s);
    UserPrincipal userPrincipal = new UserPrincipal(user);

    return userPrincipal;
  }
}
