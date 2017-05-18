package com.kyle.ie.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kyle.ie.auth.models.UserType;

@Repository
public interface IUserTypeRepository extends JpaRepository<UserType, Long> {

	UserType findBy_type(String type);
}
