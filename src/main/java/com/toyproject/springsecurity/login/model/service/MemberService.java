package com.toyproject.springsecurity.login.model.service;

import com.toyproject.springsecurity.login.model.dto.MemberDTO;

public interface MemberService {
    void registMember(MemberDTO member);

    boolean selectMemberById(String memberId);
}
