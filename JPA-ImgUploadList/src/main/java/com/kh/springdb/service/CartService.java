package com.kh.springdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.springdb.model.Cart;
import com.kh.springdb.model.CartItem;
import com.kh.springdb.model.Item;
import com.kh.springdb.model.Orders;
import com.kh.springdb.repository.*;

import jakarta.transaction.Transactional;

@Service
//@RequiredArgsConstructor
public class CartService {
  @Autowired
  private CartItemRepository carItemRepository;

  @Autowired
  private ItemRepository itemRepository;
  
  @Autowired
  private OrdersRepository ordersRepository;

  @Autowired
  private CartRepository cartRepository;

  public List<CartItem> findCartItemByCartId(int cartId) {
      return carItemRepository.findCartItemByItemId(cartId);
  }

  public List<CartItem> findByItemId(int itemId) {
      return carItemRepository.findByItemId(itemId);
  }

  public Cart getCartById(Long cartId) {
      return cartRepository.findById(cartId).orElse(null);
  }

	@Transactional
	public void addCart(Long cartId, Item newItem, int amount) {
	    // 현재 담긴 장바구니가 없을 때 장바구니 생성해주는 코드
	    Cart cart = cartRepository.findById(cartId).orElseGet(() -> {
	        Cart newCart = new Cart();
	        return cartRepository.save(newCart);
	    });

	    // 장바구니에 해당 아이템이 이미 담겨져 있는지 확인
	    CartItem cartItem = carItemRepository.findByCartIdAndItemId(cartId, newItem.getId());

	    if (cartItem == null) {
	        // 장바구니에 해당 아이템이 없으면 새로운 CartItem 생성
	        cartItem = new CartItem();
	        cartItem.setCart(cart);
	        cartItem.setItem(newItem);
	        cartItem.setCount(amount);
	    } else {
	        // 장바구니에 해당 아이템이 이미 담겨져 있으면 수량 증가
	        cartItem.addCount(amount);
	    }

	    // 생성 또는 업데이트된 CartItem을 저장
	    carItemRepository.save(cartItem);

	 
	}
	//결제하기
	@Transactional
	public void checkout(Long cartId) {
		//주문할 아이템 정보를 찾기 위해 Cart entity 정보를 가지고 옴
		Cart cart = cartRepository.findById(cartId).orElse(null);
		//Cart - 어떤 유저가 장바구니에 물건을 담았는지
		//user - cart 를 연결해주는 역할을 함
		
		//CartItem - 장바구니에 어떤 아이템이 담겼는지
		//Cart - Item 연결해놓은 역할을 함
		
		//카트가 null값이 아닐때
		if(cart != null) {
			//Order 객체를 가지고 온것임
		  //Order order = Order     +     cart(cart)   =build()
			Orders orders = Orders.builder().cart(cart).build();
			
			//결제이후 문제가 생길 것을 대비해서 DB안에도 주문한 사람과 주문 날짜와 같은 주문 내역을 저장할 예정
			ordersRepository.save(orders);
			
			//delete clear
			cart.getCartItems().clear();
			carItemRepository.deleteAll();
		}
	}

	
	
}