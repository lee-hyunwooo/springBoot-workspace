package com.kh.springdb.controller;

import com.kh.springdb.model.vo.Item;
import com.kh.springdb.service.ItemService;

import java.io.IOException;
import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequiredArgsConstructor
// @NotNull로 표시된 필드를 사용해서 생성자를 생성
public class ItemController {
	private final ItemService itemService;
	
    @GetMapping("/")
    public String mainPageNoneLogin(Model model) {
        // 로그인을 안 한 경우
        List<Item> items = itemService.allItemView();
        model.addAttribute("items", items);

        return "/main";
    }

    // 상품 등록 페이지 - 판매자만 가능
    @GetMapping("/item/new")
    public String itemSaveForm(Model model) {
            return "itemForm";
      
    }

    // 상품 등록 (POST) - 판매자만 가능
    @PostMapping("/item/new")
    public String itemSave(Item item, MultipartFile imgFile) throws Exception {
     
            itemService.saveItem(item, imgFile);
            return "redirect:/main";
       
    }
	


	//삭제
	@GetMapping("/delete/{id}")
	public String deleteItem(@PathVariable("id") Integer id) {
		itemService.itemDelete(id);
		return "itemList";
	}
}







