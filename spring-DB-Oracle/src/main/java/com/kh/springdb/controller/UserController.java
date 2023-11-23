package com.kh.springdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kh.springdb.model.User;
import com.kh.springdb.service.UserService;
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	//전체 아이디를 가지고 오기 위해서 GetMapping사용
	@GetMapping("/users")
	public String getAllUsers(Model model) {
		List<User> users = userService.getAllusers();
		model.addAttribute("users",users);
		return "users-infomation";
	}
	//하나의 아이디 가지고 오기
	@GetMapping("/users/{id}")
	public String getUserById(@PathVariable int id, Model model) {
		User user = userService.getUserById(id);
		model.addAttribute("user",user);
		return "user-info";
	}
}

/*
 @PathVariable : 경로에 대한 변수를 메서드의 매개변수로 받을 때 사용
 사용법 : @PathVariable int id
 	{id}
 
  
  
  
  
 * */
