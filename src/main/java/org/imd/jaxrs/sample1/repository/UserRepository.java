package org.imd.jaxrs.sample1.repository;

import org.imd.jaxrs.sample1.model.domain.UserType;
import org.imd.jaxrs.sample1.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByIcnAndType(String ssn, UserType type);

}