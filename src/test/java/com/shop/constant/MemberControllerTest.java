package com.shop.constant;

import com.shop.Service.MemberService;
import com.shop.dto.MemberFormDto;
import com.shop.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc //가짜 객체 웹 브라우저에 요청 하는 것처럼 테스트를 할 수 있는 가짜 객체
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberControllerTest {
    @Autowired
    private MemberService memberService;

    @Autowired //MockMvc 를 사용하기 위해 어노테이션으로 추가
    private MockMvc mockMvc;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember(String email, String password){
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail(email);
        memberFormDto.setName("홍길동");
        memberFormDto.setAddress("서울시 마포구 합정동");
        memberFormDto.setTell("010");
        memberFormDto.setPassword(password);
        Member member= Member.createMember(memberFormDto,passwordEncoder);
        return memberService.saveMember(member);
    }

    @Test
    @DisplayName("로그인성공 테스트")
    public void loginSuccessTest() throws Exception{
        String email = "test@eamil.com";
        String password = "1234";
        this.createMember(email,password);
        mockMvc.perform(formLogin().userParameter("email") //formLogin -> spring Scecurity 에 있는 로그인 동작
                .loginProcessingUrl("/members/login").user(email).password(password)).
                andExpect(SecurityMockMvcResultMatchers.authenticated()); //andExpect ,authenticated -> 동작이 올바르게 되는것을 확인
    }
    @Test
    @DisplayName("로그인실패 테스트")
    public void loginFailTest() throws Exception{
        String email = "test@eamil.com";
        String password = "1234";
        this.createMember(email,password);
        mockMvc.perform(formLogin().userParameter("email") //formLogin -> spring Scecurity에 있는 로그인 동작
                        .loginProcessingUrl("/members/login").user(email).password("12345")).
                andExpect(SecurityMockMvcResultMatchers.unauthenticated()); //andExpect ,unauthenticated -> 동작이 실패 되는것을 확인

    }

}