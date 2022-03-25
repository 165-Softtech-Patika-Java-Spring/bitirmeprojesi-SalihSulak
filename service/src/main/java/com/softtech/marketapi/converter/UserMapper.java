package com.softtech.marketapi.converter;

import com.softtech.marketapi.dto.request.UserSaveRequestDto;
import com.softtech.marketapi.dto.request.UserUpdateRequestDto;
import com.softtech.marketapi.dto.response.UserSaveResponseDto;
import com.softtech.marketapi.dto.response.UserUpdateResponseDto;
import com.softtech.marketapi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserSaveRequestDto userToSaveRequest(User user);

    UserSaveResponseDto userToSaveResponse(User user);

    User saveResponseToUser(UserSaveResponseDto userSaveResponseDto);

    User saveRequestToUser(UserSaveRequestDto userSaveRequestDto);

    User updateRequestToUser(UserUpdateRequestDto userUpdateRequestDto);

    UserUpdateResponseDto userToUpdateResponse(User user);
}
