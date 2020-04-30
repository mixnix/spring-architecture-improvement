package com.example.demo.config;

import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;


@Configuration
@RequiredArgsConstructor
public class SecurityExpressions {

    private final UserRepository userRepository;

    @Transactional
    public boolean hasPersonId(Authentication authentication, Long personId) {

        String username;
        if(authentication.getPrincipal() instanceof UserDetails){
            username = ((UserDetails)authentication.getPrincipal()).getUsername();
        }else{
            return false;
        }
        if (userRepository.findByUsername(username).isPresent()){
            Long userPersonId = userRepository.findByUsername(username).get().getPerson().getId();
            return userPersonId.equals(personId);
        }else{
            return false;
        }
    }
}
