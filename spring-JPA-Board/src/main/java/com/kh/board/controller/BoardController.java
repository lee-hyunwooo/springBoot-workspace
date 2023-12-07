package com.kh.board.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.board.service.BoardService;
import com.kh.board.vo.Board;

@Controller
@RequestMapping("/boards")
public class BoardController {
	
	private final BoardService boardService;
	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@GetMapping
	public String getAllBoards(Model model) {
		List<Board> boards = boardService.getAllBoards();
		model.addAttribute("boards", boards);
		return "board_list";
	}
	
	//게시물 상세보기 컨트롤러
	@GetMapping("/details/{boardId}")
	public String getBoardDetails(@PathVariable Long boardId, Model model) {
		Optional<Board> board = boardService.getBoardById(boardId);
		board.ifPresent(value -> model.addAttribute("board",value));
		return "board_detail";
	}
	
	@GetMapping("/new")
	public String displayBoardForm(Model model) {
		model.addAttribute("board", new Board());
		return "board_form";
	}
	
	@PostMapping("/save")
	public String saveBoard(@ModelAttribute Board board) {
		boardService.saveBoard(board);
		return "redirect:/boards";
	}
	//수정 폼 제공
	@GetMapping("/update/{boardId}")
	public String updateboard(@PathVariable Long boardId, Model model) {
		Optional<Board> board = boardService.getBoardById(boardId);
		board.ifPresent(value -> model. addAttribute("board",value));
		return "board_update";
	}
	//수정 기능
	@PostMapping("/update/{boardId}")
	public String updateBoard(@ModelAttribute Board board) {
		boardService.saveBoard(board);
		return "redirect:/boards";
	}
	
	@GetMapping("/delete/{boardId}")
	public String deleteboard(@PathVariable Long boardId) {
		boardService.deleteBoardById(boardId);
		return "redirect:/boards";
	}
	//모두삭제
	@GetMapping("/deleteAll")
	public String deleteAllBoards() {
		boardService.deleteAllBoards();
		return "redirect:/boards";
	}
	@GetMapping("/search")
    public String searchBoards(@RequestParam String keyword, Model model) {
        // 특정 키워드를 포함하는 게시물 검색
        List<Board> boards = boardService.findBoardsByTitle(keyword);

        // 모델에 검색 결과 추가
        model.addAttribute("boards", boards);

        // 검색 결과를 보여줄 뷰 페이지 리턴
        return "searchResults";
    }
}
