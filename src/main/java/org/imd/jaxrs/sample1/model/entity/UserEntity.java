package org.imd.jaxrs.sample1.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.imd.jaxrs.sample1.model.domain.UserType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(indexes = {
    @Index(unique = true, columnList = "icn ASC, type DESC")
})
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_post")
    @SequenceGenerator(name = "seq_post", initialValue = 1000, allocationSize = 1)
    private Long id;

    private String icn;

    private String fname;
    private String lname;

    // @Enumerated(EnumType.STRING)
    private UserType type;
}
