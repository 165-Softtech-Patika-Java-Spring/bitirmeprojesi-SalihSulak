package com.softtech.marketapi.service.entityservice;

import com.softtech.marketapi.entity.User;
import com.softtech.marketapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserEntityServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserEntityService userEntityService;

    @Test
    void shouldSaveUser() {
        User user = mock(User.class);
        user.setUsername("test");
        user.setSurname("test");
        user.setUsername("test");

        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userEntityService.saveUser(user);

        assertEquals(user,savedUser);
    }

    @Test
    void shouldFindById() {
        UUID uuid = UUID.randomUUID();
        User user = mock(User.class);
        user.setId(uuid);
        when(userRepository.findById(uuid)).thenReturn(Optional.of(user));

        User findedUser = userEntityService.findById(uuid);

        assertEquals(user,findedUser);
    }

    @Test
    void shouldFindByIdIsNull(){
        UUID uuid = UUID.randomUUID();

        when(userRepository.findById(uuid)).thenReturn(null);
        assertThrows(NullPointerException.class, () -> userEntityService.findById(uuid));
    }

    @Test
    void shoulFindByUsername() {
        User user = mock(User.class);
        user.setUsername("test");
        when(userRepository.findByUsername(any())).thenReturn(Optional.of(user));

        User findedUser = userEntityService.findByUsername("test");

        assertEquals(user,findedUser);
    }

    @Test
    void shouldFindByUsernameIsNull(){
        when(userRepository.findByUsername(any())).thenReturn(null);
        assertThrows(NullPointerException.class, () -> userEntityService.findByUsername(null));
    }

}
