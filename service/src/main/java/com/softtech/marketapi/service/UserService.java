package com.softtech.marketapi.service;

import com.softtech.marketapi.converter.UserMapper;
import com.softtech.marketapi.dto.request.UserSaveRequestDto;
import com.softtech.marketapi.dto.request.UserUpdateRequestDto;
import com.softtech.marketapi.dto.response.UserSaveResponseDto;
import com.softtech.marketapi.dto.response.UserUpdateResponseDto;
import com.softtech.marketapi.entity.User;
import com.softtech.marketapi.generic.entity.BaseAdditionalFields;
import com.softtech.marketapi.service.entityservice.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityService userEntityService;
    private final PasswordEncoder passwordEncoder;

    public UserSaveResponseDto saveUser(UserSaveRequestDto userSaveRequestDto){
        User user = UserMapper.INSTANCE.saveRequestToUser(userSaveRequestDto);
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        BaseAdditionalFields baseAdditionalFields = user.getBaseAdditionalFields();

        if (Objects.isNull(baseAdditionalFields)) {
            baseAdditionalFields = new BaseAdditionalFields();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            baseAdditionalFields.setCreateDate(LocalDateTime.now().format(formatter));

            user.setBaseAdditionalFields(baseAdditionalFields);
        }

        userEntityService.saveUser(user);
        UserSaveResponseDto userSaveResponseDto = UserMapper.INSTANCE.userToSaveResponse(user);
        return userSaveResponseDto;
    }

    public UserUpdateResponseDto updateUser(UserUpdateRequestDto updateRequest){
        User updatedUser = getUpdatedUser(updateRequest);
        userEntityService.saveUser(updatedUser);

        UserUpdateResponseDto userUpdateResponseDto = UserMapper.INSTANCE.userToUpdateResponse(updatedUser);
        return userUpdateResponseDto;
    }

    public UUID deleteUser(UUID id){
        User user = userEntityService.findById(id);
        userEntityService.deleteUser(user);
        return user.getId();
    }

    private User getUpdatedUser(UserUpdateRequestDto updateRequest) {
        UUID userId = updateRequest.getId();
        User user = userEntityService.findById(userId);
        if (StringUtils.hasText(updateRequest.getName()))
            user.setName(updateRequest.getName());

        if(StringUtils.hasText(updateRequest.getUsername()))
            user.setUsername(updateRequest.getUsername());

        if (StringUtils.hasText(updateRequest.getSurname()))
            user.setSurname(updateRequest.getSurname());
        return user;
    }
}
