package com.softtech.marketapi.service.entityservice;

import com.softtech.marketapi.dto.request.UserSaveRequestDto;
import com.softtech.marketapi.dto.response.UserSaveResponseDto;
import com.softtech.marketapi.entity.User;
import com.softtech.marketapi.enums.errors.UserErrorMessages;
import com.softtech.marketapi.generic.exceptions.GenericBusinessException;
import com.softtech.marketapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserEntityService {

    private final UserRepository userRepository;

    public User saveUser(User user){
        userRepository.save(user);
        return user;
    }
    public User findById(UUID id){
        User user = userRepository.findById(id)
                .orElseThrow(()->new GenericBusinessException(UserErrorMessages.USER_NOT_FOUND));
        return user;
    }
    public User findByUsername(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(()->new GenericBusinessException(UserErrorMessages.USER_NOT_FOUND));

        return user;
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }

}
