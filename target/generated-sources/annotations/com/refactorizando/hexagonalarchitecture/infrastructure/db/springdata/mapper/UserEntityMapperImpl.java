package com.refactorizando.hexagonalarchitecture.infrastructure.db.springdata.mapper;

import com.refactorizando.hexagonalarchitecture.domain.User;
import com.refactorizando.hexagonalarchitecture.infrastructure.db.springdata.dbo.UserEntity;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-09T22:12:59-0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 11.0.14 (Oracle Corporation)"
)
@Component
public class UserEntityMapperImpl implements UserEntityMapper {

    @Override
    public User toDomain(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        User user = new User();

        user.setId( userEntity.getId() );
        user.setName( userEntity.getName() );
        user.setAddress( userEntity.getAddress() );

        return user;
    }

    @Override
    public UserEntity toDbo(User user) {
        if ( user == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( user.getId() );
        userEntity.setName( user.getName() );
        userEntity.setAddress( user.getAddress() );

        return userEntity;
    }
}
