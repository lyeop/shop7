package com.shop.config;

import com.shop.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //웹 보안을 가능하게 한다.
public class SecurityConfig {
    @Autowired
    MemberService memberService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{
        http.authorizeRequests(auth->auth.
                requestMatchers("/css/**","/img/**","/favicon.ico","/error").permitAll().
                requestMatchers("/","/members/**","item/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated())
                .formLogin(formLogin -> formLogin.loginPage("/members/login")
                        .defaultSuccessUrl("/").usernameParameter("email").
                        failureUrl("/members/login/error"));
        //관리자 로그인, 유저로그인 성공,실패 했을때의 메소드 연결
        http.exceptionHandling(exception->exception.
                authenticationEntryPoint(new CustomAuthenticationEntryPoint()));
        //관리자가 아닌사람이 관리자 탭을 쓸때 안되게 하는 메소드를 연결
        return http.build();
    }
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }


}
