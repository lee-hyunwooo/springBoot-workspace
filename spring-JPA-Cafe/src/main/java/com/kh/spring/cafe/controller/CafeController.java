package com.kh.spring.cafe.controller;
import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.cafe.service.CafeService;
import com.kh.spring.cafe.vo.Cafe;

@Controller
@RequestMapping
public class CafeController {
	private final CafeService cafeService;
	
	@Autowired
	public CafeController(CafeService cafeService) {
		this.cafeService = cafeService;
	}
	
	@GetMapping()
	public String getAllCafes(Model model,@RequestParam(required=false) String name) {
		
		
		//카페리스트를 만들어준 후 
		//만약에 리스트에서 카페가 존재한다면 그 카페목록들만 보여주고
		//만약에 존재하지 않는다면 그냥 모든 카페 내용을 보여주겠다
		List<Cafe> cafes;
		if(name != null && !name.isEmpty()) {
			cafes = cafeService.findCafes(name);
		}else {
			cafes = cafeService.getAllCafes();
		}
		
		model.addAttribute("cafes", cafes);
		return "cafeList";
	}
	
	@GetMapping("/new")
	public String showCafeForm(Model model) {
		model.addAttribute("cafe", new Cafe());
		return "cafeForm";
	}
	
	@PostMapping("/save")
	public String saveCafe(@ModelAttribute Cafe cafe) {
		cafeService.saveCafe(cafe);
		return "redirect:/cafes";
	}
	
	@GetMapping("/update/{cafeId}")
	public String updateCafe(@PathVariable Long cafeId, Model model) {
		Optional<Cafe> cafe = cafeService.getCafeById(cafeId);
		cafe.ifPresent(value -> model.addAttribute("cafe",value));
		return "cafeForm";
	}
	
	@GetMapping("/delete/{cafeId}")
	public String deleteCafe(@PathVariable Long cafeId) {
		cafeService.deleteCafeById(cafeId);
		return "redirect:/cafes";
	}
	/*
	@GetMapping("/search")
	public String searchCafes(@RequestParam String keyword, Model model) {
		// 특정 키워드를 포함하는 카페를 검색
		List<Cafe> cafes = cafeService.findCafes(keyword);
		
		//모델에 검색 결과 추가
		model.addAttribute("cafes", cafes);
		
		//검색 결과를 보여줄 뷰 페이지 작성!
		return "searchResults";
	}
	*/
}


//@RequestParam(required=false) : 파라미터를 필수로 적어주니 않아도 된다는것을 나타냄
		//@RequestParam http 요청으로 파라미터를 메서드의 매개변수로 전달할 때 사용
		//	클라이언트가 웹 애플리케이션에 보내는 요처의 파라미터 값을 받아서 처리하는데 사용
		//@PathVariable 과 @RequestParam 의 차이
		//	@PathVariable : URL 경로에서 변수 값을 추출 url /cafes/{id}
		//	@RequestParam : 한 경로 안에서 클라이언트가 요청한 파라미터 값을 추출
							//url /cafes?name=사용자가 폼에 입력한 값





