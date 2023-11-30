package com.kh.cafe.controller;

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

import com.kh.cafe.service.CafeService;
import com.kh.cafe.vo.Cafe;

@Controller
@RequestMapping("/cafes")
public class CafeController {
	
	
	
	@Autowired
	private CafeService cafeService;
	
	
	@GetMapping
    public String getAllCafes(Model model,@RequestParam(required=false) String name) {
        List<Cafe> cafes;
        
        if(name != null && !name.isEmpty()) {
        	cafes = cafeService.findCafes(name);
    	}else {
    		cafes = cafeService.getAllCafes();
    	}

        model.addAttribute("cafes", cafes);
        return "cafe_list";
    }
	//리스트를 만들어준 후 
			//만약에 리스트에서 카페가 존재한다면 그 카페목록들만 보여주고 
			//만약에 존재하지 않는다면 그냥 모든 카페 내용을 보여주겠다.
			//@RequestParam(required=false) : 파라미터를 필수로 적어주지 않아도 됨을 나타냄
			//@RequestParam : http 요청으로 파라미터를 메서드의 매개변수로 전달할 때 사용 
			//클라이언트가 웹 애플리케이션에 보내는 요청의 파라미터 값을 받아서 처리하는데 사용 
			
			
			//@PathVariable 과 @RequestParam의 차이 : 
			// @PathVariable: URL 경로에서 변수 값을 추출 url /cafes/{id}
			// @RequestParam : 한 경로안에서 클라이언트가 요청한 파라미터 값을 추출 url /cafes?name=사용자가 폼에 입력한 값이 들어감 
	
	
	//카페 상세보기 
	@GetMapping("/detail/{id}")
	public String getCafeById(@PathVariable Long id, Model model) {
		Optional<Cafe> cafe = cafeService.getCafeById(id);
		cafe.ifPresent(value -> model.addAttribute("cafe", value));
		return "cafe_detail";
	}
	
	@GetMapping("/new")
	public String displayCafeForm(Model model) {
		model.addAttribute("cafe",new Cafe());
		return "cafe_form";
	}
	
	@PostMapping("/save")
	public String saveCafe(@ModelAttribute Cafe cafe) {
		cafeService.saveCafe(cafe);
		return "redirect:/cafes";
	}
	
	@GetMapping("/update/{id}")
	public String updateCafe(@PathVariable Long id, Model model) {
		Optional<Cafe> cafe = cafeService.getCafeById(id);
		cafe.ifPresent(value -> model.addAttribute("cafe", value));
		return "cafe_form";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteCafe(@PathVariable Long id) {
		cafeService.deleteCafeById(id);
		return "redirect:/cafes";
	}
	
	@GetMapping("/delete/all")
	public String deleteAllCafes() {
		cafeService.deleteAllCafes();
		return "redirect:/cafes";
	}
	
	/*
	@GetMapping("/search")
	public String searchCafes(@RequestParam String keyword, Model model) {
		List<Cafe> cafes = cafeService.findCafes(keyword);
		model.addAttribute("cafes", cafes);
		return "searchResults";
		
	}
	*/
	
	

}
