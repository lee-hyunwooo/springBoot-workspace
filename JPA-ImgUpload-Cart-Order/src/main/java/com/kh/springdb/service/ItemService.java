package com.kh.springdb.service;

import com.kh.springdb.model.vo.Item;
import com.kh.springdb.repository.ItemRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
public class ItemService {
	private ItemRepository itemRepository;
	
	@Autowired
	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}
	// 상품을 추가하고 삭제하고 수정하는 기능
	public void addItem(Item item, MultipartFile photoFile) throws IllegalStateException, IOException {
		
		//상품명 저장될 파일명 경로 생성
		//이미지 파일 정보에 대해서 추출
		String originPhtoName = photoFile.getOriginalFilename();
		String phtoName = "";
		//업로드된 이미지 파일의 원본 파일명을 가져옴
		
		String photoPath =System.getProperty("user.dir") + "src/main/resources/static/uploadImg/";//업로드 된 이미지 파일 경로 설정
		//System.getProperty("user.dir")
		//System.getProperty() : 작업하고 있는 폴더 위치
		// 			"user.dir" : 현재 작업하고 있는 사용자 폴더를 나타냄
		// user.dir 은
		//C:/Users/user1/springBoot-workspace/JPA-ImgUpload-Cart-Order 
		//이 위치와 동일함
		
		String saveFileName = "khShop_" + originPhtoName;
		// saveFileName으로 만약에 판매자가 사진1을 올리면
		// 내 폴더 안에는 khShop_사진1 로 저장이됨
		
		//빈 값에다가 한 번 더 재정의로 넣어줌
		phtoName = saveFileName;
		
		//import java.io.File;
		//파일을 저장하기 위해 우리가 작성해놓은 파일을
		//작성하기 위한 공간이 비어있는 File 객체를 가지고 옴
		//						파일을저장할경로  파일명
		File saveFile = new File(photoPath, phtoName);
		
		// 만약에 이름을 변경해서 저장하고 싶지 않다면
		// originPhtoName으로 저장해도 됨
		// 판매자 컴퓨터에 있는 이미지 이름으로 그대로 업로드 됨
		//File saveFile = new File(photoPath, originPhtoName);
		
		//업로드 된 이미지 파일을 지정된 경로에 저장하기 위해 설정
		//transferTo 서버에 특정 경로에 저장할 수 있도록해주는 메서드
		photoFile.transferTo(saveFile);
		item.setPhotoName(phtoName);
		item.setPhtoPath("/img/" + phtoName);
		
		// DB에 저장할 수 있도록 save
		itemRepository.save(item);
	}
	
	// 상품 읽기 find를 사용해서 개별읽기
	public Item getItemById(Integer id) {
		return itemRepository.findById(id).get();
	}
	//  findById를 사용해서 Id에 해당하는 값을 가져오고
	// get을 이용해서 Item 객체를 반환
	

	// 모든 상품 가지고오기 List
	public List<Item> allItemList() {
		return itemRepository.findAll();
	}
	
	public void itemDelete(Integer Id) {
		itemRepository.deleteById(Id);
	}
}







