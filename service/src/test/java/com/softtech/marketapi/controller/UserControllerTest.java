package com.softtech.marketapi.controller;

import com.softtech.marketapi.dto.request.UserUpdateRequestDto;
import com.softtech.marketapi.dto.response.UserUpdateResponseDto;
import com.softtech.marketapi.entity.User;
import com.softtech.marketapi.generic.dto.RestResponse;
import com.softtech.marketapi.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @Test
    void updateUser() {
        UserUpdateRequestDto userUpdateRequestDto = mock(UserUpdateRequestDto.class);
        UserUpdateResponseDto userUpdateResponseDto = mock(UserUpdateResponseDto.class);

        when(userService.updateUser(any())).thenReturn(userUpdateResponseDto);
        ResponseEntity<RestResponse<UserUpdateResponseDto>> result = userController.updateUser(userUpdateRequestDto);
        assertEquals(200,result.getStatusCodeValue());
    }

    @Test
    void deleteUser() {
        UUID uuid = UUID.randomUUID();
        when(userService.deleteUser(uuid)).thenReturn(uuid);
        ResponseEntity<RestResponse<UUID>> result = userController.deleteUser(uuid);
        assertEquals(200,result.getStatusCodeValue());
    }
}
