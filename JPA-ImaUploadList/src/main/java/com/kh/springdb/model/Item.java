package com.kh.springdb.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="ITEMS")
public class Item {
	@Id
	@GeneratedValue(strategy =GenerationType.SEQUENCE, generator="item_seq")
	@SequenceGenerator(name="item_seq",sequenceName="item_seq",allocationSize=1)
	private int id;
	
	//물건이름
	private String name;
	
	//물건 설명
	private String text;
	
	//물건 가격
	private String price;
	
	//물건 개수
	private int count;
	
	//재고
	private int stock;
	
	//상품 품절 유무
	private int isSoldout;
	
	//이미지 업로드를 위한 파일명, 이미지 경로, 상품 등록 날짜
	private String imgName;
	private String imgPath;
	
	@DateTimeFormat(pattern= "yyyy-mm-dd")
	private LocalDate createDate;
	
	//DB에 값을 넣을 때 자동으로 생성된 날짜가 들어감
	@PrePersist
	public void createDate() {
		this.createDate = LocalDate.now();
	}
	
	//판매자가 누구인지, 장바구니에 어떤 아이템이 들어가져 있는지 x
	
	
}
