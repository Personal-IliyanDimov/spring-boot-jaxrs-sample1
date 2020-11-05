package org.imd.jaxrs.sample1.model.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserType {
    BUYER("B"),
    SELLER("S");

    private final String abbr;
}