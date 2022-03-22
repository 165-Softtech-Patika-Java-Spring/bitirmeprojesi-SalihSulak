package com.softtech.marketapi.security.service;

import com.softtech.marketapi.dto.request.UserSaveRequestDto;
import com.softtech.marketapi.dto.response.UserSaveResponseDto;
import com.softtech.marketapi.entity.User;
import com.softtech.marketapi.security.security.CustomUserDetails;
import com.softtech.marketapi.security.security.TokenGenerator;
import com.softtech.marketapi.security.dto.LoginRequestDto;
import com.softtech.marketapi.security.enums.JwtConstant;
import com.softtech.marketapi.service.UserService;
import com.softtech.marketapi.service.entityservice.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenGenerator tokenGenerator;
    private final UserEntityService userEntityService;

    public UserSaveResponseDto register(UserSaveRequestDto userSaveRequestDto) {
        UserSaveResponseDto userSaveResponseDto = userService.saveUser(userSaveRequestDto);
        return userSaveResponseDto;
    }

    public String login(LoginRequestDto loginRequestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenGenerator.generateJwtToken(authentication);

        String bearer = JwtConstant.BEARER.getConstant();
        return bearer + token;
    }

    public User getCurrentUser() {

        CustomUserDetails userDetails = getCurrentUserDetails();

        User user = null;
        if (Objects.nonNull(userDetails))
            user = userEntityService.findById(userDetails.getId());

        return user;
    }

    public UUID getCurrentUserId(){
        CustomUserDetails currentUserDetails = getCurrentUserDetails();
        return currentUserDetails.getId();
    }

    private CustomUserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails = null;
        if(Objects.nonNull(authentication) && authentication.getPrincipal() instanceof CustomUserDetails){
            userDetails = (CustomUserDetails) authentication.getPrincipal();
        }
        return userDetails;
    }
}
