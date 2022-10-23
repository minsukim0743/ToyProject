package com.toyproject.springsecurity.config;


import com.toyproject.springsecurity.login.model.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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

    private final AuthenticationService authenticationService;
    @Autowired
    public SpringSecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

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
                .authorizeRequests()
                .mvcMatchers("/**", "/member/**").permitAll()
                .and()
                .csrf().disable();

        //로그인 로그아웃 설정
        http
                .formLogin()
                .loginPage("/member/login")             //커스텀 로그인 페이지 사용
                .defaultSuccessUrl("/member/loginSuccess") //로그인 성공시 이동 페이지
                .failureUrl("/member/loginFail") // 로그인 실패시 이동 페이지
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
