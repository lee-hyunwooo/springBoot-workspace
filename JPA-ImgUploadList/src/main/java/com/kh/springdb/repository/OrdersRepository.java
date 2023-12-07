package com.kh.springdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.springdb.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long>{

}
