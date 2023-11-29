package com.kh.springdb.controller;

import com.kh.springdb.service.ProductService;
import com.kh.springdb.vo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product_list";
    }

    @GetMapping("/details/{id}")
    public String getProductDetails(@PathVariable Long id, Model model) {
        Optional<Product> product = productService.getProductById(id);
        product.ifPresent(value -> model.addAttribute("product", value));
        return "product_detail";
    }
    



    @GetMapping("/new")
    public String showProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }
    @GetMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, Model model) {
    	Optional<Product> product = productService.getProductById(id);
    	product.ifPresent(value -> model.addAttribute("product", value));
    	return "product_form";
    	
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }
}

/*
Optional<Product> product = productService.getProductById(id);
Optional 안에는  productService.getProductById(id)로 id 값을 가져와서
	 id에 해당하는 제품을 가지고 옴
	그런데 여기서 만약 id에 해당하는 제품이 존재하지 않는다면
	Optional 은 비어있게 됨
	만약에 Optional이 비어있게 된다면 에러가 발생할 수 있지만(현재는)
	추후 비어있을 경우를 대비해서 예외 값을 처리해주는 것이 좋음
	예외값을 처리하는 방법 : orElse 를 이용해서 대체값을 제공하거나
					페이지 이동 처리를 할 수 있음 
					(ex:error.html)
			이외에 orElseGet 대체값을 생성하는 함수를 제공
			           orElseThrow : 예외를 던짐
product.ifPresent(value -> model.addAttribute("product", value));

ifPresent :  Optional 객체 안에 값이 존재할 경우 람다식 표현을 실행하기 위한 메서드
	Optional 객체 안에 값이 존재할 경우 실행할 것임
	 value 값이 존재하면 모델에 product 변수명을 사용해서
			product 안에  value 값을 추가할 것
	추가된 product는 html 템플릿 안에서 product를 thymeLeaf를 통해
	호출해서 value값을 사용할 수 있음

람다식 : 간결하게 함수를 표현하는 방법으로 간단하게 결과를 표현할 때 사용
	기본코드 : (변수값) -> 변수값이 존재하거나 어떤일을 
			       발생할 경우의 결과를 작성

if (value != null){
	 model.addAttribute("product", value)
}




public Int c(int a, int b){
	int d = a + b;
	return d;
}

public static void main(String[] args){
	int a = 3;
	int b = 4;
	c(a,b)
	System.out.println("c의 값은 " + c);

 * 
 * */


