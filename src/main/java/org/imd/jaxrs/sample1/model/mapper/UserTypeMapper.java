package org.imd.jaxrs.sample1.model.mapper;

import org.imd.jaxrs.sample1.model.domain.UserType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;

import java.util.Objects;

@Mapper(componentModel = "spring")
public abstract class UserTypeMapper {

    String toString(UserType userType) {
        if (Objects.isNull(userType)) {
            return null;
        }

        String result;
        switch (userType) {
            case BUYER:
                result = "dto-buyer";
                break;
            case SELLER:
                result = "dto-seller";
                break;
            default:
                throw new IllegalArgumentException("User type unknown value: " + userType);
        }

        return result;
    }

    UserType toUserType(String dtoUserType) {
        if (Objects.isNull(dtoUserType)) {
            return null;
        }

        UserType result;
        switch (dtoUserType) {
            case "dto-buyer":
                result = UserType.BUYER;
                break;
            case "dto-seller":
                result = UserType.SELLER;
                break;
            default:
                throw new IllegalArgumentException("User type unknown value: " + dtoUserType);
        }

        return result;
    }
}
