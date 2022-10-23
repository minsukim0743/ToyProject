package com.toyproject.springsecurity.login.model.service;

import com.toyproject.springsecurity.login.model.dto.MemberDTO;

public interface MemberService {
    /* 회원가입 */
    void registMember(MemberDTO member);

    /* 회원가입시 아이디 중복체크 */
    boolean selectMemberById(String memberId);
}
