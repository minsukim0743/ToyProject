package com.toyproject.springsecurity.login.model.dao;

import com.toyproject.springsecurity.login.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper{

    /* 회원가입 */
    int insertMember(MemberDTO member);

    /* 회원가입시 아이디 중복체크 */
    String selectMemberById(String memberId);

    /* 로그인 아이디 찾기 */
    MemberDTO findByMemberId(String memberId);

    /* DB 이메일 인증코드 설정 */
    int updateEmailCode(MemberDTO member);

    /* 이메일 인증코드 조회 */
    MemberDTO selectEmailCode(MemberDTO member);

    /* 비밀번호 재설정 */
    int updatePwd(MemberDTO member);

    /* 이메일 조회 */
    MemberDTO selectEmail(MemberDTO member);
}
