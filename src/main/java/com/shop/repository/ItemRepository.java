package com.shop.repository;

import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

//JpaRepository << 쿼리문 전송
public interface ItemRepository extends JpaRepository<Item, Long> ,
        QuerydslPredicateExecutor<Item> , ItemRepositoryCustom{
    // QuerydslPredicateExecutor -> EntityManager 사용대신
    List<Item> findByItemNm(String itemNm);
    //find + entity + By << select *from item where =?(String itemNm)

    List<Item> findByItemNmOrItemDetail(String itemNm,String itemDetail);
    //findBy + 칼럼 + Or + 칼럼
    List<Item> findByPriceLessThan(Integer price);
    //finBy + 칼럼 + LessThan 미만인 값을 출력

    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);
    @Query("Select i from Item i where i.itemDetail Like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail")String itemDetail);

    @Query(value = "select * from item i where i.item_Detail like %:itemDetail% order by i.price desc",nativeQuery = true)
    List<Item> findByItemDetailNative(@Param("itemDetail")String itemDetail);
}
