package com.facebook.web.repository;

import com.facebook.web.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository
            extends JpaRepository<BoardEntity, Integer> {

    List<BoardEntity> findAllByOrderByBnoDesc();
}
