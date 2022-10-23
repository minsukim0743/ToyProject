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

    @GetMapping("/confirmEmail")
    public String confirmEmailPage(){

        return "/member/confirmEmail";
    }

    @PostMapping("/confirmEmail")
    public ModelAndView confirmEmail(ModelAndView mv, MemberDTO member, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {

        member.setMemberId(request.getParameter("memberId"));
        member.setEmail(request.getParameter("email"));

        MemberDTO selectEmail = emailService.selectEmail(member);

        log.info("");
        log.info("");
        log.info("[EmailController] email : " + selectEmail);

        if(selectEmail.getEmail().equals(member.getEmail())){

            int result = emailService.updateEmailCode(member);

            if(result > 0){

                mv.addObject("mvMemberId", member.getMemberId());
                mv.addObject("mvEmail", member.getEmail());

                log.info("");
                log.info("");
                log.info("[EmailController] mvMemberId : " + member.getMemberId());
                log.info("[EmailController] mvEmail : " + member.getEmail());

                mv.setViewName("/member/updatePwd");
            }

        } else {

            mv.setViewName("/member/emailFail");
        }

        return mv;
    }

    @PostMapping("/updatePwd")
    public String sendEmail(MemberDTO member, HttpServletRequest request, HttpServletResponse response, RedirectAttributes rttr){

        member.setMemberId(request.getParameter("memberId"));
        member.setEmail(request.getParameter("email"));
        member.setMemberPwd(passwordEncoder.encode(request.getParameter("newPwd")));
        member.setEmailCode(request.getParameter("code"));

        String url = "";

        log.info("");
        log.info("");
        log.info("[EmailController] member : " + member);

        MemberDTO emailCode = emailService.selectEmailCode(member);

        if(emailCode.getEmailCode().equals(member.getEmailCode())){

            member.setEmailCode(emailCode.getEmailCode());
            int result = emailService.updatePwd(member);

            if(result > 0){
                SessionUtil.invalidateSession(request, response);
                url = "redirect:/";
                rttr.addFlashAttribute("message", "비밀번호 재설정을 성공하였습니다.");
            }
        } else {

            url = "/member/emailFail";
        }

        return url;
    }
}
