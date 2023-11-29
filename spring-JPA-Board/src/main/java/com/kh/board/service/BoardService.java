package com.kh.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.board.repository.BoardRepository;
import com.kh.board.vo.Board;

@Service
public class BoardService {
	private final BoardRepository boardRepository;
	
	@Autowired
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	//게시물 전체 조회
	public List<Board> getAllBoards() {
		return boardRepository.findAll();
	}
	//게시물 상세 조회
	public Optional<Board> getBoardById(Long id) {
		return boardRepository.findById(id);
	}
	//게시물 작성
	public Board saveBoard(Board board) {
		return boardRepository.save(board);
	}
	
	
	//게시물 삭제
	public void deleteBoardById(Long id) {
		boardRepository.deleteById(id);
	}
	//게시물 모두 삭제하기
	public void deleteAllBoards() {
		boardRepository.deleteAll();
	}
}
