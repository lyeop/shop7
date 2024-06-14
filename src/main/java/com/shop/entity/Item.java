package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@Entity //db에 저장
@Table(name="item") //테이블명 item
@Getter
@Setter
@ToString
public class Item {
    @Id  //PK
    @Column(name="item_id") //칼럼 이름
    @GeneratedValue(strategy = GenerationType.AUTO) //PK auto로 증가
    private Long id; // 상품코드

    @Column(nullable = false,length = 50) //not null 길이 50
    private String itemNm; //상품명

    @Column(name="Price",nullable = false) //칼럼이름 price not null
    private int price; //가격

    @Column(nullable = false) //not null
    private int stockNumber; //수량

    @Lob  //대용량 저장
    @Column(nullable = false) // not null
    private String itemDetail; //상품상세설명

    @Enumerated(EnumType.STRING) // Enum을 String 으로 변환해서 저장
    private ItemSellStatus itemSellStatus; //상품판매 상태

    private LocalDateTime regTime; //등록시간

    private LocalDateTime updateTime; //수정시간
}
