package com.toyproject.springsecurity.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity // springSecurity를 사용하기위한 어노테이션
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


//    @Autowired
//    public SpringSecurityConfig(MemberService memberService){
//
//        this.memberService = memberService;
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web){

        web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/lib/**");
    }

    /* HTTP요청에 대한 권한 설정 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 페이지 접근 권한
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll()
        // 로그인 로그아웃 설정
          .and()
                .formLogin()
                .loginPage("/member/login")
                .successForwardUrl("/member/success")
                .failureUrl("/member/fail")
          .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        System.out.println("확인");
//        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
//    }
}
