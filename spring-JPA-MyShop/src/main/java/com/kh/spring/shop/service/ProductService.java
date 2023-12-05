package com.kh.spring.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.shop.repository.MemberRepository;
import com.kh.spring.shop.repository.ProductRepository;
import com.kh.spring.shop.vo.Member;
import com.kh.spring.shop.vo.Product;

@Service
public class ProductService {
	
	
	    private final ProductRepository productRepository;
	    // 생성자 위한 어노테이션으로
	 			//스프링은 ProductRepository 타입의 빈을 찾아서 주입
	 	
	    @Autowired
	    public ProductService(ProductRepository productRepository) {
	        this.productRepository = productRepository;
	    }

	    // 모든 상품을 조회하는 메서드 
	 	//Repository  findAll 지정된 메서드를 가지고와서 사용
	 	public List<Product> getAllProducts() {
	        return productRepository.findAll();
	    }
	 	
	 	//상품 1개만 조회하는 메서드
	    public Optional<Product> getProductById(Long id) {
	        return productRepository.findById(id);
	    }
	  //저장하는 메서드 
	    // 최초로 작성한 내용 저장 
	    // 기존에 작성한 내용 수정해서 저장
	    public Product saveProduct(Product product) {
	        return productRepository.save(product);
	    }
	  //삭제하는 메서드
	    public void deleteProductById(Long id) {
	        productRepository.deleteById(id);
	    }
	}
