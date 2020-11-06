package org.imd.jaxrs.sample1.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long id;

    private String icn;

    private String fname;
    private String lname;

    private String type;
}
