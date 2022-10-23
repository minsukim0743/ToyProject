package com.toyproject.springsecurity.email.service;

import com.toyproject.springsecurity.login.model.dto.MemberDTO;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailService {
    int updateEmailCode(MemberDTO member) throws MessagingException, UnsupportedEncodingException;

    MemberDTO selectEmailCode(MemberDTO member);

    int updatePwd(MemberDTO member);

    MemberDTO selectEmail(MemberDTO member);
}
