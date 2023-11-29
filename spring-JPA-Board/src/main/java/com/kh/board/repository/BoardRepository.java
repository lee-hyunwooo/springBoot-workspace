package com.kh.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.board.vo.Board;


public interface BoardRepository extends JpaRepository<Board, Long> {

}
