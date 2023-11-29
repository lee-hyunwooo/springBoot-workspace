package com.kh.springdb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.kh.springdb.model.Product;

public interface ProductMapper {
	
    List<Product> getAllProducts();
}