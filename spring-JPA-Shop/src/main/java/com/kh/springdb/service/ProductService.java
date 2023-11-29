package com.kh.springdb.service;

import com.kh.springdb.repository.ProductRepository;
import com.kh.springdb.vo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
	//인터페이스를 사용해서 데이터베이스와 상호 작용하는 데 사용할
	//레포지토리를 나타내는 필드
	//final 선언돼서 변경이 불가능
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
//저장하는 메서드