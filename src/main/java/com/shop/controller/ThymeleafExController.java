package com.shop.controller;

import com.shop.dto.ItemDto;
import com.shop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/thymeleaf")
public class ThymeleafExController {
    //localhost/thymeleaf/ex01 -> thymeleafEx01.html
    // ${data} ->Hello World 나오도록 출력


    @GetMapping(value ="/ex01")
    public String thymeleafEx01(Model model){

        model.addAttribute("data","Hello World");
        return "thymeleafEx/thymeleafEx01";
    }

    @GetMapping(value ="/ex02")
    public String thymeleafEx02(Model model){

        ItemDto itemDto = new ItemDto();
        itemDto.setItemDetail("상품 상세 설명");
        itemDto.setItemNm("테스트 상품1");
        itemDto.setPrice(10000);
        itemDto.setRegTime(LocalDateTime.now());
        model.addAttribute("itemDto",itemDto);
        return "thymeleafEx/thymeleafEx02";
    }
    @GetMapping(value ="/ex03")
    public String thymeleafEx03(Model model){
        ArrayList<ItemDto> itemList = new ArrayList<ItemDto>();
        for(int i=1; i<=10; i++) {
            ItemDto itemDto = new ItemDto();
            itemDto.setItemDetail("상품 상세 설명"+ i);
            itemDto.setItemNm("테스트 상품"+ i);
            itemDto.setPrice(10000+i);
            itemDto.setRegTime(LocalDateTime.now());
            itemList.add(itemDto);
        }
        model.addAttribute("itemDtoList",itemList);
            return "thymeleafEx/thymeleafEx03";
    }
    @GetMapping(value ="/ex04")
    public String thymeleafEx04(Model model){
        ArrayList<ItemDto> itemList = new ArrayList<ItemDto>();
        //arraylist를 이용해 10개의 내용을 저장하고 model에 itemlist 데이터를 넘김
        for(int i=1; i<=10; i++) {
            ItemDto itemDto = new ItemDto();
            itemDto.setItemDetail("상품 상세 설명"+ i);
            itemDto.setItemNm("테스트 상품"+ i);
            itemDto.setPrice(10000+i);
            itemDto.setRegTime(LocalDateTime.now());
            itemList.add(itemDto);
        }
        model.addAttribute("itemDtoList",itemList);
        return "thymeleafEx/thymeleafEx04";
    }
    @GetMapping(value = "/ex05")
    public String thymeleafEx05(Model model){
        return "thymeleafEx/thymeleafEx05";
    }
    @GetMapping(value ="/ex06")
    public String thymeleafEx06(@RequestParam("param1")String name,@RequestParam("param2")String name2, Model model){
            model.addAttribute("param1",name);
            model.addAttribute("param2",name2);
        return "thymeleafEx/thymeleafEx06";
    }
    @GetMapping(value ="/ex07")
    public String thymeleafEx07(){
        return "thymeleafEx/thymeleafEx07";
    }

}
