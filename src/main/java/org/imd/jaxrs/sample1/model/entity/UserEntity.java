package org.imd.jaxrs.sample1.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.imd.jaxrs.sample1.model.domain.UserType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(indexes = {
    @Index(unique = true, columnList = "icn ASC, type DESC")
})
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String icn;

    private String fname;
    private String lname;

    @Enumerated(EnumType.STRING)
    private UserType type;
}
