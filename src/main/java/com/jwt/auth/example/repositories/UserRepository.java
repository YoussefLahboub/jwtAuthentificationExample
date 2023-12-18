package com.jwt.auth.example.repositories;

import com.jwt.auth.example.models.UserInfos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfos, Long> {
     Optional<UserInfos> findByUsername(String username);
}
