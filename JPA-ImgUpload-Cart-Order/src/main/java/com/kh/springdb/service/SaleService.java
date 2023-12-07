package com.kh.springdb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.springdb.model.vo.*;
import com.kh.springdb.repository.ItemRepository;
import com.kh.springdb.repository.SaleItemRepository;
import com.kh.springdb.repository.SaleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;
    private final SaleItemRepository saleItemRepository;
    private final ItemRepository itemRepository;

    // 회원가입 하면 판매자 당 판매내역 하나 생성
    public void createSale (User user){

        Sale sale = Sale.createSale(user);

        saleRepository.save(sale);
    }


}