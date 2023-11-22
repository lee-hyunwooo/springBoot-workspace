package com.kh.springDBOraclepre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.springDBOraclepre.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/boardList")
	public String displayDoardList(Model model) {
		model.addAttribute("board",boardService.getAllBoards());
		return "boardList";
	}
}
