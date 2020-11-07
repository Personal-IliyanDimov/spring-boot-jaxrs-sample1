package org.imd.jaxrs.sample1.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.imd.jaxrs.sample1.model.dto.anno.FnameLnameLengthCheck;
import org.imd.jaxrs.sample1.model.dto.group.CreateGroup;
import org.imd.jaxrs.sample1.model.dto.group.UpdateGroup;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@FnameLnameLengthCheck
public class UserDto {

    @Null(groups = {CreateGroup.class})
    @NotNull(groups = {UpdateGroup.class})
    private Long id;

    @NotEmpty(groups = {CreateGroup.class, UpdateGroup.class})
    @Size(min = 8, max = 8, groups = {CreateGroup.class, UpdateGroup.class})
    private String icn;

    @NotEmpty(groups = {CreateGroup.class, UpdateGroup.class})
    @Size(min = 1, max = 127, groups = {CreateGroup.class, UpdateGroup.class})
    private String fname;

    @NotEmpty(groups = {CreateGroup.class, UpdateGroup.class})
    @Size(min = 1, max = 127, groups = {CreateGroup.class, UpdateGroup.class})
    private String lname;

    @NotEmpty(groups = {CreateGroup.class, UpdateGroup.class})
    @Pattern(regexp = "dto-buyer|dto-seller", groups = {CreateGroup.class, UpdateGroup.class})
    private String type;
}
