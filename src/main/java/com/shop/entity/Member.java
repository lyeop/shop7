package com.shop.entity;

import com.shop.constant.Role;
import com.shop.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity // 나 엔티티야
@Table(name="member") //테이블 명
@Getter
@Setter
@ToString
public class Member {
    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    //기본키 , 칼럼명 member_id  AI -> 자동으로 숫자증가
    private long id;
    private String name;
    @Column(updatable = true)
    //중복 허용 x
    private String email;
    private String password;
    private String address;
    @Enumerated(EnumType.STRING)
    //Enum -> 컴 : 숫자 우리 : 문자
    //데이터베이스 문자 그대로 -> user, admin
    private Role role;
    @Column(updatable = true)
    private String tell;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
        member.setTell(memberFormDto.getTell());
        String password =passwordEncoder.encode(memberFormDto.getPassword());
        //password 암호화
        member.setPassword(password);
        member.setRole(Role.ADMIN);
        return member;
    }
}
