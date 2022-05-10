package com.refactorizando.hexagonalarchitecture.infrastructure.db.springdata.mapper;

import com.refactorizando.hexagonalarchitecture.domain.User;
import com.refactorizando.hexagonalarchitecture.infrastructure.db.springdata.dbo.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    User toDomain(UserEntity userEntity);

    UserEntity toDbo(User user);

}
