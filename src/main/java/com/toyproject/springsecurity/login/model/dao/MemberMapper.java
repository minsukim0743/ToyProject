package com.toyproject.springsecurity.login.model.dao;

import com.toyproject.springsecurity.login.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper{

    /* 회원가입 등록 */
    int insertMember(MemberDTO member);

    /* 회원가입 등록한 member 권한 부여 */
    int insertMemberRole(String memberId);
}
