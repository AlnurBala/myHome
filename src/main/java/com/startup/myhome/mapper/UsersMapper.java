package com.startup.myhome.mapper;

import com.startup.myhome.dto.request.UsersRequest;
import com.startup.myhome.dto.response.UsersResponseDto;
import com.startup.myhome.entity.Users;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface UsersMapper {
    Users fromDTO(UsersRequest userRequest);

    UsersResponseDto toDTO(Users users);

    List<UsersResponseDto> toDTOs(List<Users> users);

    Users mapUpdateRequestToEntity(@MappingTarget Users user, UsersRequest userRequest);
    Users mapUpdateResponseToEntity(@MappingTarget Users user, UsersResponseDto usersResponseDto);


}
