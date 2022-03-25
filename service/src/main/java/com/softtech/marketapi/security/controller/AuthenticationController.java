package com.softtech.marketapi.security.controller;

import com.softtech.marketapi.dto.request.UserSaveRequestDto;
import com.softtech.marketapi.dto.response.UserSaveResponseDto;
import com.softtech.marketapi.generic.dto.RestResponse;
import com.softtech.marketapi.security.dto.LoginRequestDto;
import com.softtech.marketapi.security.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("login")
    @Operation(tags = "User Controller")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto){
        String token = authenticationService.login(loginRequestDto);

        return ResponseEntity.ok(RestResponse.of(token));
    }

    @PostMapping("register")
    @Operation(tags = "User Controller")
    public ResponseEntity register(@RequestBody UserSaveRequestDto userSaveRequestDto){
        UserSaveResponseDto userResponse = authenticationService.register(userSaveRequestDto);
        return ResponseEntity.ok(RestResponse.of(userResponse));
    }
}
