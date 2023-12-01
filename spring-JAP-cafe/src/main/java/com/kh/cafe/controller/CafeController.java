package com.kh.cafe.controller;
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

import com.kh.cafe.service.CafeService;
import com.kh.cafe.vo.Cafe;
@Controller
@RequestMapping("/cafes")
public class CafeController {
	    @Autowired
	    private CafeService cafeService;
	    /*
	    @GetMapping
	    public String getAllCafes(Model model) {
	        List<Cafe> cafes = cafeService.getAllCafes();
	        model.addAttribute("cafes", cafes);
	        return "cafeList";
	    }
	    
	    		List<Cafe> cafes;
			if(/*만약에 카페이름 값이 빈 값이 아니거나 null값이 아니라면) {
				-> 사람들이 검색한 카페 내용을 service에서 가져와서 뿌린다음에
				cafes에 넣어버리겠다.
			}else {
				->모든 카페 리스트를 보여주겠다.
			}
			
	*/
	    
	    @GetMapping
	    public String getAllCafes(Model model, @RequestParam(required = false) String name) {
	        List<Cafe> cafes;

	        if (name != null && !name.isEmpty()) {
	            cafes = cafeService.searchCafes(name);
	        } else {
	            cafes = cafeService.getAllCafes();
	        }

	        model.addAttribute("cafes", cafes);
	        return "cafeList";
	    }

	    @GetMapping("/{id}")
	    public String getCafeById(@PathVariable Long id, Model model) {
    	  Optional<Cafe> cafe = cafeService.getCafeById(id);
    	  cafe.ifPresent(value -> model.addAttribute("cafe", value));
	      //model.addAttribute("cafe", cafe);
	      return "cafeDetail";
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
	    @GetMapping("/update/{id}")
	    public String updateBoard(@PathVariable Long id, Model model) {
	        Optional<Cafe> cafe = cafeService.getCafeById(id);
	        cafe.ifPresent(value -> model.addAttribute("cafe", value));
	        return "cafeForm";
	    }

	    @GetMapping("/delete/{id}")
	    public String deleteCafe(@PathVariable Long id) {
	        cafeService.deleteCafe(id);
	        return "redirect:/cafes";
	    }
	    
	    //GetMappig을 활용해서 count 해준 location을 가지고오기
	    @GetMapping("/count/{location}")
	    public String countCafesByLocation(@PathVariable String location, Model model) {
	    	int cafeCount = cafeService.countCafesByLocation(location);
	    	// 1. 지역값을 저장할 모델
	    	// 2. 지역 갯수를 저장해줄 모델
	    	model.addAttribute("location", location);
	    	model.addAttribute("cafeCount", cafeCount);
	    	return "cafeCount";
	    }
	    /*
	     	@PathVariable이 아니라 @RequestParam으로 값을 가져오는 경우
		    @GetMapping("/count")
		    public String countCafesByLocation(@RequestParam String location, Model model) {
		        int cafeCount = cafeService.countCafesByLocation(location);
		        model.addAttribute("cafeCount", cafeCount);
		        model.addAttribute("location", location);
		        return "cafeCount";
		    }
	     * */
	    //카페가 존재하는지 확인여부
	    @GetMapping("/exists/{name}")
	    public String existsCafeByName(@PathVariable String name, Model model) {
	    	boolean cafeExists = cafeService.existsCafeByName(name);
	    	model.addAttribute("cafeExists", cafeExists);
	    	return "cafeExists";
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

/*
@GetMapping
public String getAllCafes(Model model) {
    List<Cafe> cafes = cafeService.getAllCafes();
    model.addAttribute("cafes", cafes);
    return "cafeList";
}

		List<Cafe> cafes;
	if(/*만약에 카페이름 값이 빈 값이 아니거나 null값이 아니라면) {
		-> 사람들이 검색한 카페 내용을 service에서 가져와서 뿌린다음에
		cafes에 넣어버리겠다.
	}else {
		->모든 카페 리스트를 보여주겠다.
	}
	
*/

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
//@RequestParam(required=false) : 파라미터를 필수로 적어주지 않아도 됨을 나타냄
//@RequestParam http 요청으로 파라미터를 메서드의 매개변수로 전달할 때
//클라이언트가 웹 애플리케이션에 보내는 요청의 파라미터 값을
//받아서 처리하는데 사용
// @PathVariable 과 @RequestParam 의 차이
// @PathVariable : URL 경로에서 변수 값을 추출 url /cafes/{id}
// @RequestParam : 한 경로 안에서 클라이언트가 요청한 파라미터 값을 추출 
						//url /cafes?name=사용자가 폼에 입력한 값