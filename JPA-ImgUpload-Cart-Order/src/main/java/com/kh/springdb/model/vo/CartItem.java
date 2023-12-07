package com.kh.springdb.model.vo;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cartItem_seq")
	@SequenceGenerator(name = "cartItem_seq", sequenceName="cartItem_seq",allocationSize=1)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="item_id")
    private Item item;

    private int count; // 상품 개수

    public static CartItem createCartItem(Cart cart, Item item, int amount) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setItem(item);
        cartItem.setCount(amount);
        return cartItem;
    }

    // 이미 담겨있는 물건 또 담을 경우 수량 증가
    public void addCount(int count) {
        this.count += count;
    }

}
/*
@ManyToOne(fetch=FetchType.EAGER)
여러 엔티티가 하나의 엔티티에 감싸져서 활용되는 N:1 관계를 나타냄
fetch=FetchType.EAGER : 가져오기, 감싸진(매핑된) 엔티티를
자바에서 검색할 때(찾고자할 때) 바로 로딩해서 가져올 수 있도록 설정해준 것

 * */





