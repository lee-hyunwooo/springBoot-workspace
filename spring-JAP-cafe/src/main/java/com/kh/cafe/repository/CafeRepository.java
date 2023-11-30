package com.kh.cafe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.kh.cafe.vo.Cafe;

import jakarta.persistence.Column;

public interface CafeRepository extends JpaRepository<Cafe, Long> {
	//특정 문자열을 포함한 엔티티를 검색하는데 사용하는 메서드
	List<Cafe>findByNameContaining(String keyword);
	 //@Query("SELECT b FROM Cafe b WHERE b.name LIKE %:keyword%")
	 //1. 만약에 보여줄 것이 많다. => list로 담아서 한 번에 보여주기
	    //List<Cafe> findCafe(@Param("keyword") String keyword);
	 		// find By
			// count By
	 //2. 보여줄 것이 하나 => cafe 객체 하나만 호출할 것 
	 

}

/*
 Query Creation : Spring Data JPA 에서 제공하는 기능
 메서드에 규칙이 존재하고 규칙에 따라서 메서드를 생성해주는 기능
 메서드 이름으로 데이터베이스 쿼리를 생성
 
 	findBy+내가찾고싶은변수명 
 	예를들어 CAFE 라는 class에 작성해놓은
 	private Long cafe_id;
	private String name;
	private String location;
	private String opening_hours;에서
	지역을 검색하고 싶다면
		findByLocation(String location)
		=> SELECT * FROM cafe WHERE location = ?
	운영시간을 검색하고 싶다면
		findByOpeningHours(String openinghours)
		=> SELECT * FROM Cafe WHERE openinghours = ?
		
		findByNameContaining(String keyword)
		Containing => 해당하는 변수명이 특정 변수에 대한 검색을 Like로 진행할 수 있게 도와줘
		
	총 갯수를 계산해주는 메서드를 만들고 싶다면
	countBy 클래스에 적어준 변수명
		countByLocation(String location)
		=> SELECT COUNT(*) FROM CAFE WHERE LOCATION =?
		
	존재 여부를 확인해주는 메서드를 만들고 싶다면
	existBy 클래스에 적어준 변수명
		existByLocation(String location)
		=> SELECT CASE WHEN COUNT(*) > 0 THEN true
					   						ELSE false
					   	END FROM Cafe WHERE location =?
	
	만약에 삭제하고 싶다면
	deleteBy 클래스에 적어준 변수명
		deleteByLocation(String location)
		=> DELETE FROM CAFE WHERE LOCATION = ?
	
	
 * */
