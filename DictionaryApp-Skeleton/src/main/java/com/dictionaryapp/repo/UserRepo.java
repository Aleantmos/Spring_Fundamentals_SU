package com.dictionaryapp.repo;

import com.dictionaryapp.model.entity.User;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Registered
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);

    User findUserById(Long id);
}
