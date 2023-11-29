package com.kh.board.vo;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Setter
@Table(name="Board")
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="board_id_sequence")
	@SequenceGenerator(name="board_id_sequence", sequenceName="board_id_sequence",allocationSize=1)
	@Column(name="board_id")
	private Long boardId;
	
	@Column(name="title")	
	private String title;
	
	@Column(name="content")
	private String content;
	
	@Column(name="author")
	private String author;
	
}
