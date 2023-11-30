package com.kh.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kh.board.vo.Board;


public interface BoardRepository extends JpaRepository<Board, Long> {

	@Query("SELECT b FROM Board b WHERE b.title LIKE %:keyword%")
    List<Board> findTitle(@Param("keyword") String keyword);
}
