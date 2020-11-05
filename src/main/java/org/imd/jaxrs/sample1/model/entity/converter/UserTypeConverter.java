package org.imd.jaxrs.sample1.model.entity.converter;

import org.imd.jaxrs.sample1.model.domain.UserType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class UserTypeConverter implements AttributeConverter<UserType, String> {

    @Override
    public String convertToDatabaseColumn(UserType userType) {
        if (userType == null) {
            return null;
        }
        return userType.getAbbr();
    }

    @Override
    public UserType convertToEntityAttribute(String abbr) {
        if (abbr == null) {
            return null;
        }

        return Stream.of(UserType.values())
            .filter(ut -> ut.getAbbr().equals(abbr))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }
}
