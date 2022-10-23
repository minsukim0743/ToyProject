package com.toyproject.springsecurity.email.service;

import com.toyproject.springsecurity.email.common.MailHandler;
import com.toyproject.springsecurity.email.common.TempKey;
import com.toyproject.springsecurity.login.model.dao.MemberMapper;
import com.toyproject.springsecurity.login.model.dto.MemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Service
public class EmailServiceImpl implements EmailService{

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final MemberMapper memberMapper;
    private final JavaMailSender mailSender;

    @Autowired
    public EmailServiceImpl(MemberMapper memberMapper, JavaMailSender mailSender){

        this.memberMapper = memberMapper;
        this.mailSender = mailSender;
    }

    /* DB 이메일 인증코드 설정 */
    @Override
    @Transactional
    public int updateEmailCode(MemberDTO member) throws MessagingException, UnsupportedEncodingException {

        /* 랜덤 인증코드를 member EmailCode에 set한다. */
        String emailCode = new TempKey().getKey(10, false);
        member.setEmailCode(emailCode);

        log.info("");
        log.info("");
        log.info("[EmailServiceImpl] memberEmailCode " + member.getEmailCode());

        /* 이메일 인증코드 설정한다. */
        int result = memberMapper.updateEmailCode(member);

        /* 이메일 인증코드 설정 성공시 */
        if(result > 0){
            MailHandler sendMail = new MailHandler(mailSender);
            sendMail.setSubject("[인증 이메일입니다.]");
            sendMail.setText(
                    "<h1>이메일 인증</h1>" +
                            "<br>비밀번호를 재설정하기 위한 인증 이메일입니다." +
                            "<br>아래 인증번호를 입력해주세요." +
                            "<br>[" + emailCode + "]"
            );
            sendMail.setFrom("minsukim0743@naver.com","김민수");
            sendMail.setTo(member.getEmail());
            sendMail.send();
        }

        return result;
    }

    /* 이메일 인증코드 조회 */
    @Override
    public MemberDTO selectEmailCode(MemberDTO member) {

        MemberDTO emailCode = memberMapper.selectEmailCode(member);

        return emailCode;
    }

    /* 비밀번호 재설정 */
    @Override
    @Transactional
    public int updatePwd(MemberDTO member) {

        int result = memberMapper.updatePwd(member);

        return result;
    }

    /* 이메일 조회 */
    @Override
    public MemberDTO selectEmail(MemberDTO member) {

        MemberDTO selectEmail = memberMapper.selectEmail(member);

        return selectEmail;
    }
}
