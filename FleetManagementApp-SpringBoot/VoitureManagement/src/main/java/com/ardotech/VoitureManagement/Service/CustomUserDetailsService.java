package com.ardotech.VoitureManagement.Service;

import com.ardotech.VoitureManagement.Entity.User;
import com.ardotech.VoitureManagement.Enum.UserStatus;
import com.ardotech.VoitureManagement.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private  UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        if (user.getStatus() != UserStatus.ACTIVE) {
            throw new IllegalStateException("User account is not active");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getMotDePasse(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }

}
