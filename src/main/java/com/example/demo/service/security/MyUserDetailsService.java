package com.example.demo.service.security;

import com.example.demo.domain.dao.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByUsername(email);
            if (user == null) {
                throw new UsernameNotFoundException("No user found with username: " + email);
            }

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                    user.isEnabled(),user.isAccountNonExpired(), user.isCredentialsNonExpired(),
                    user.isAccountNonLocked(), user.getAuthorities());
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}