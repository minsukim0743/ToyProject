package com.toyproject.springsecurity.config;


import com.toyproject.springsecurity.login.model.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity // springSecurity를 사용하기위한 어노테이션
@RequiredArgsConstructor
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationService authenticationService;
    private final AuthenticationFailureHandler customFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web){

        /* 인증 무시 설정 */
        web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/lib/**");
    }

    /* HTTP요청에 대한 권한 설정 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //권한별 접근 페이지 설정
        http
                .authorizeRequests() // 리소스의 권한 설정
                .mvcMatchers("/**", "/member/**").permitAll() // antMatchers 설정한 리소스의 접근을 인증절차 없이 허용한다는 의미
                .and()
                .csrf().disable();

        //로그인 로그아웃 설정
        http
                .formLogin()
                .loginPage("/member/login")             // 커스텀 로그인 페이지 사용
                .defaultSuccessUrl("/main") //로그인 성공시 이동 페이지
                .failureHandler(customFailureHandler) // 로그인 실패시 이동 페이지
                .usernameParameter("memberId")			// 아이디 파라미터명 설정
                .passwordParameter("memberPwd")			// 패스워드 파라미터명 설정
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/");

    }

}
