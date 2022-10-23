package com.toyproject.springsecurity.email.service;

import com.toyproject.springsecurity.login.model.dto.MemberDTO;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailService {

    /* DB 이메일 인증코드 설정 */
    int updateEmailCode(MemberDTO member) throws MessagingException, UnsupportedEncodingException;

    /* 이메일 인증코드 조회 */
    MemberDTO selectEmailCode(MemberDTO member);

    /* 비밀번호 재설정 */
    int updatePwd(MemberDTO member);

    /* 이메일 조회 */
    MemberDTO selectEmail(MemberDTO member);
}
