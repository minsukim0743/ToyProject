package com.toyproject.springsecurity.email.controller;

import com.toyproject.springsecurity.common.util.SessionUtil;
import com.toyproject.springsecurity.email.service.EmailService;
import com.toyproject.springsecurity.login.model.dto.MemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/member/*")
public class EmailController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final EmailService emailService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EmailController(EmailService emailService, PasswordEncoder passwordEncoder) {
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    /* 비밀번호 찾기 페이지 이동 */
    @GetMapping("/confirmEmail")
    public String confirmEmailPage(){

        return "/member/confirmEmail";
    }

    @PostMapping("/confirmEmail")
    public ModelAndView confirmEmail(ModelAndView mv, MemberDTO member, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {

        member.setMemberId(request.getParameter("memberId"));
        member.setEmail(request.getParameter("email"));

        /* 비밀번호를 찾기 위해 해당 ID DB에 저장된 email을 조회한다. */
        MemberDTO selectEmail = emailService.selectEmail(member);

        log.info("");
        log.info("");
        log.info("[EmailController] email : " + selectEmail);

        /* 해당 ID의 email과 입력한 email이 같은지 조회한다 */
        if(selectEmail.getEmail().equals(member.getEmail())){

            /* email이 같을시 이메일 인증코드를 발송한다. */
            int result = emailService.updateEmailCode(member);

            /* 이메일 인증코드 발송 성공시 */
            if(result > 0){

                mv.addObject("mvMemberId", member.getMemberId());
                mv.addObject("mvEmail", member.getEmail());

                log.info("");
                log.info("");
                log.info("[EmailController] mvMemberId : " + member.getMemberId());
                log.info("[EmailController] mvEmail : " + member.getEmail());

                /* 비밀번호 재설정 페이지로 이동한다. */
                mv.setViewName("/member/updatePwd");
            }

        } else {

            /* 실패시 Fail페이지로 이동 */
            mv.setViewName("/member/emailFail");
        }

        return mv;
    }

    /* 비밀번호 재설정 */
    @PostMapping("/updatePwd")
    public String sendEmail(MemberDTO member, HttpServletRequest request, HttpServletResponse response, RedirectAttributes rttr){

        /* 비밀번호 재설정을 위하여 setting 해준다. */
        member.setMemberId(request.getParameter("memberId"));
        member.setEmail(request.getParameter("email"));
        member.setMemberPwd(passwordEncoder.encode(request.getParameter("newPwd")));
        member.setEmailCode(request.getParameter("code"));

        String url = "";

        log.info("");
        log.info("");
        log.info("[EmailController] member : " + member);

        /* 입력된 이메일 인증코드와 발송된 이메일 인증코드가 같은지 조회한다. */
        MemberDTO emailCode = emailService.selectEmailCode(member);

        if(emailCode.getEmailCode().equals(member.getEmailCode())){

            /* 인증코드가 같을시 비밀번호 재설정을 한다.*/
            member.setEmailCode(emailCode.getEmailCode());
            int result = emailService.updatePwd(member);

            if(result > 0){

                /* 비밀번호 재설정 성공시 세션을 초기화 해준다. */
                SessionUtil.invalidateSession(request, response);

                /* 비밀번호 재설정 성공시 Home으로 이동한다. */
                url = "redirect:/";

                /* alert창으로 message를 띄워주기 위해 넘겨준다. */
                rttr.addFlashAttribute("message", "비밀번호 재설정을 성공하였습니다.");
            }
        } else {

            /* 실패시 Fail 페이지로 이동 */
            url = "/member/emailFail";
        }

        return url;
    }
}
