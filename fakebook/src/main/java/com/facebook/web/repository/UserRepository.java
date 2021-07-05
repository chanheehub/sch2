package com.facebook.web.repository;


import com.facebook.web.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository
        extends JpaRepository<UserEntity, String> {

    @EntityGraph(attributePaths = {"roleSet"},
                type = EntityGraph.EntityGraphType.LOAD)
    Optional<UserEntity> findByEmail(String email);

}
