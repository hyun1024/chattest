package com.sparta.springsecondwork.repository;

import com.sparta.springsecondwork.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByOrderByCreatedAtDesc();
    List<Board> findAllByUsername(String username);

}
