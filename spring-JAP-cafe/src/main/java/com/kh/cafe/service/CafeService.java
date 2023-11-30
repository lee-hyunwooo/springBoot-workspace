package com.kh.cafe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.cafe.repository.CafeRepository;
import com.kh.cafe.vo.Cafe;

@Service
public class CafeService {

	@Autowired
	private  CafeRepository cafeRepository;
	
	
	
	
	// 카페 전체조회
	public List<Cafe> getAllCafes(){
		return cafeRepository.findAll();
	}
	
	// 카페 상세 정보 
	public Optional<Cafe> getCafeById(Long id){
		return cafeRepository.findById(id);
	}
	
	// 카페 추가
	public Cafe saveCafe(Cafe cafe) {
		return cafeRepository.save(cafe);
	}
	
	//카페 하나 삭제
	public void deleteCafeById(Long id) {
		cafeRepository.deleteById(id);
	}
	
	//카페 전체 삭제
	public void deleteAllCafes() {
		cafeRepository.deleteAll();
	}
	
	
	
	
	public List<Cafe> findCafes(String keyword){
		return cafeRepository.findByNameContaining(keyword);
		
	}
	
	

	
}
