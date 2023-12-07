package com.kh.springdb.model.vo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sale_seq")
	@SequenceGenerator(name = "sale_seq", sequenceName="sale_seq",allocationSize=1)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="seller_id")
    private User seller; // 판매자

    @OneToMany(mappedBy = "sale")
    private List<SaleItem> saleItems = new ArrayList<>();

    private int totalCount; // 총 판매 개수

    public static Sale createSale(User seller) {
        Sale sale = new Sale();
        sale.setSeller(seller);
        sale.setTotalCount(0);
        return sale;
    }

}