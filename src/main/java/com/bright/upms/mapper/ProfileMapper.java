package com.bright.upms.mapper;

import com.bright.upms.dto.request.ProfileRequestDto;
import com.bright.upms.model.Profile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    Profile profileRequestDtoToProfile(ProfileRequestDto profileRequestDto);

}
