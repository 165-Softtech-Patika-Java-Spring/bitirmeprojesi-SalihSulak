package com.softtech.marketapi.security.security;

import com.softtech.marketapi.entity.User;
import com.softtech.marketapi.generic.exceptions.GenericBusinessException;
import com.softtech.marketapi.service.entityservice.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserEntityService userEntityService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = null;
        try {
            user = userEntityService.findByUsername(username);
        } catch (GenericBusinessException e) {
            e.printStackTrace();
        }
        return CustomUserDetails.create(user);
    }

    public UserDetails loadById(UUID id) {
        User user = null;
        try {
            user = userEntityService.findById(id);
        } catch (GenericBusinessException e) {
            e.printStackTrace();
        }
        return CustomUserDetails.create(user);
    }
}
