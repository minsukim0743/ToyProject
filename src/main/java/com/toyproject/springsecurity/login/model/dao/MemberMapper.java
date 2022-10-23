package com.toyproject.springsecurity.login.model.dao;

import com.toyproject.springsecurity.login.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper{

    /* 회원가입  */
    int insertMember(MemberDTO member);

    /* 회원가입시 아이디 중복체크 */
    String selectMemberById(String memberId);
    MemberDTO findByMemberId(String memberId);
}
