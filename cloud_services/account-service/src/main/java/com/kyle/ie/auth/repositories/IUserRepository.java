package com.kyle.ie.auth.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kyle.ie.auth.models.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u LEFT JOIN FETCH u._roles r WHERE u._username=:username")
     Optional<User> findByUsername(@Param("username") String username);
    
    @Query("SELECT count(*) FROM User u where u._username=:username")
    int isUsernameUsed(@Param("username") String username);
    
    @Query("SELECT count(*) FROM User u where u._email=:email")
    int isEmailUsed(@Param("email") String email);
}
