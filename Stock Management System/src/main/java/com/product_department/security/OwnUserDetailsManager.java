package com.product_department.security;

import com.product_department.repository.UserCredentialRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OwnUserDetailsManager implements UserDetailsService {

    private UserCredentialRepo userCredentialRepo;

    public OwnUserDetailsManager(UserCredentialRepo userCredentialRepo) {
        this.userCredentialRepo = userCredentialRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userCredentialRepo.findByName(username)
                            .map(OwnUserDetails::new)
                            .orElseThrow(() -> new UsernameNotFoundException("Check your credentials : Mr/Ms"+username));

    }
}
