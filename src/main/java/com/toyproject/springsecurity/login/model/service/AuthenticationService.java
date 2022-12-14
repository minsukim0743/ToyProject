package com.toyproject.springsecurity.login.model.service;

import com.toyproject.springsecurity.login.model.dao.MemberMapper;
import com.toyproject.springsecurity.login.model.dto.MemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final MemberMapper memberMapper;

    @Autowired
    public AuthenticationService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    /* 로그인 아이디 찾기 */
    @Override
    public UserDetails loadUserByUsername(String memberId) {

        log.info("[AuthenticationService] =====================================================");
        log.info("[AuthenticationService] memberId : " + memberId);

        MemberDTO member = memberMapper.findByMemberId(memberId);

        log.info("[AuthenticationService] member : " + member);

        return member;
    }
}
