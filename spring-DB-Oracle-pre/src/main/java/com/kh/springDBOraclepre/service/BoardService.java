package com.kh.springDBOraclepre.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.springDBOraclepre.mapper.BoardMapper;
import com.kh.springDBOraclepre.model.Board;

@Service
public class BoardService {

@Autowired
private BoardMapper boardMapper;

	public List<Board> getAllBoards() {
		return boardMapper.getAllBoards();
	}
}
