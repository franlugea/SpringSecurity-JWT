package com.SpringSecurity.Repository;

import com.SpringSecurity.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findUserByName(String name);
    boolean existsUsuarioEntityByName(String name);
}
