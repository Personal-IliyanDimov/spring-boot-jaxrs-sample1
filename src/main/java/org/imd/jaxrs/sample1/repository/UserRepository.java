package org.imd.jaxrs.sample1.repository;

import org.imd.jaxrs.sample1.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<UserEntity, Long> {

}