package com.softtech.marketapi.service;

import com.softtech.marketapi.dto.request.UserSaveRequestDto;
import com.softtech.marketapi.dto.request.UserUpdateRequestDto;
import com.softtech.marketapi.dto.response.UserSaveResponseDto;
import com.softtech.marketapi.entity.User;
import com.softtech.marketapi.service.entityservice.UserEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserEntityService userEntityService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    UserService userService;

    @Test
    void shouldSaveUser() {
        UserSaveRequestDto userSaveRequestDto = mock(UserSaveRequestDto.class);
        when(userSaveRequestDto.getPassword()).thenReturn("123");

        User user = mock(User.class);
        when(user.getUsername()).thenReturn("test");

        when(passwordEncoder.encode(anyString())).thenReturn("encoded_test_123");
        when(userEntityService.saveUser(any())).thenReturn(user);

        UserSaveResponseDto result = userService.saveUser(userSaveRequestDto);

        assertEquals("test",result.getUsername());
    }

}
