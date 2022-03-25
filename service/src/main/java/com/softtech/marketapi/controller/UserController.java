package com.softtech.marketapi.controller;

import com.softtech.marketapi.dto.request.UserUpdateRequestDto;
import com.softtech.marketapi.dto.response.UserUpdateResponseDto;
import com.softtech.marketapi.generic.dto.RestResponse;
import com.softtech.marketapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PutMapping()
    @Operation(tags = "User Controller")
    public ResponseEntity<RestResponse<UserUpdateResponseDto>> updateUser(@RequestBody UserUpdateRequestDto userUpdateRequestDto){
        UserUpdateResponseDto userUpdateResponseDto = userService.updateUser(userUpdateRequestDto);
        return ResponseEntity.ok(RestResponse.of(userUpdateResponseDto));
    }

    @DeleteMapping("/{userId}")
    @Operation(tags = "User Controller")
    public ResponseEntity<RestResponse<UUID>> deleteUser(@PathVariable UUID userId){
        UUID deletedUserId = userService.deleteUser(userId);
        return ResponseEntity.ok(RestResponse.of(deletedUserId));
    }
}
