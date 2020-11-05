package org.imd.jaxrs.sample1.model.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private Long id;

    private String icn;

    private String fname;
    private String lname;

    private UserType type;
}
