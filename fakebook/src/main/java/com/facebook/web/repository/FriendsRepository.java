package com.facebook.web.repository;

import com.facebook.web.entity.BoardEntity;
import com.facebook.web.entity.FriendsEntity;
import com.facebook.web.entity.FriendsId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendsRepository
            extends JpaRepository<FriendsEntity, Integer> {


    List<FriendsEntity> findAll();
}
