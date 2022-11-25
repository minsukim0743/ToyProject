package com.toyproject.springsecurity.login.model.controller;

import com.google.gson.Gson;
import com.toyproject.springsecurity.login.model.dto.CommentDTO;
import com.toyproject.springsecurity.login.model.dto.MemberDTO;
import com.toyproject.springsecurity.login.model.service.CommentService;
import com.toyproject.springsecurity.login.model.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/member/*")
public class MemberController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;
    private final CommentService commentService;

    @Autowired
    public MemberController(PasswordEncoder passwordEncoder, MemberService memberService, CommentService commentService) {
        this.passwordEncoder = passwordEncoder;
        this.memberService = memberService;
        this.commentService = commentService;
    }

    /* 회원가입시 아이디 중복체크 */
    @PostMapping("/idDupCheck")
    public ResponseEntity<String> idDupCheck(@RequestBody MemberDTO member){

        log.info("");
        log.info("");
        log.info("[MemberController] member ID check : " + member.getMemberId());

        String result = "사용 가능한 아이디 입니다.";
        log.info("[MemberController] Request Check ID : " + member.getMemberId());

        if("".equals(member.getMemberId())) {
            log.info("[MemberController] 아이디를 입력해 주세요");
            result = "아이디를 입력해 주세요.";
        } else if(memberService.selectMemberById(member.getMemberId())) {
            log.info("[MemberController] 중복된 아이디가 존재합니다.");
            result = "중복된 아이디가 존재합니다.";
        }

        log.info("[MemberController] checkDuplication ==========================================================");

        return ResponseEntity.ok(result);
    }

    /* 회원가입 페이지 이동 */
    @GetMapping("/regist")
    public String memberRegistPage(){

        return "/member/regist";
    }

    /* 회원가입 */
    @PostMapping("/regist")
    public String memberRegist(@ModelAttribute MemberDTO member, RedirectAttributes rttr, HttpServletRequest request){

        /* 주소API를 사용하여 받아온 주소를 합쳐준다.*/
        String address = request.getParameter("zipCode") + " " + request.getParameter("address1") + " " + request.getParameter("address2");
        /* 핸드폰번호 '-' 삭제 */
        member.setPhone(member.getPhone().replace("-", ""));
        member.setAddress(address);
        /* 비밀번호 인코딩 */
        member.setMemberPwd(passwordEncoder.encode(member.getMemberPwd()));

        log.info("");
        log.info("");
        log.info("[MemberController] member : " + member);

        memberService.registMember(member);

        rttr.addFlashAttribute("message", "회원 가입에 성공하였습니다.");

        return "redirect:/";
    }

    @GetMapping("/loginFail")
    public String loginFailPage(){

        return "/member/loginFail";
    }
    
    @GetMapping("/loginSuccess")
    public ModelAndView loginSuccessPage(ModelAndView mv){
        
        // 댓글 개수 조회하기
        int totalCount = commentService.selectTotalCount();
        List<CommentDTO> commentList = commentService.commentList();

        mv.addObject("totalCount", totalCount);
        mv.addObject("commentList", commentList);
        mv.setViewName("/member/loginSuccess");

        return mv;
    }

    // 댓글 등록하기
    @PostMapping("/commentInsert")
    @ResponseBody
    public int commentInsert(@ModelAttribute CommentDTO comment){

        log.info("");
        log.info("");
        log.info("[commentInsert]" + comment);

        int result = commentService.commentInsert(comment);

        return result;
    }

    // 댓글 리스트 조회
    @GetMapping("/commentList")
    @ResponseBody
    public String commentList(){

        Gson gson = new Gson();

        List<CommentDTO> commentList = commentService.commentList();

        log.info("");
        log.info("");
        log.info("[commentList]" + commentList);

        return gson.toJson(commentList);
    }

    // 댓글 삭제
    @PostMapping("/comments/{commentNo}")
    @ResponseBody
    public int commentDelete(@PathVariable int commentNo){

        log.info("");
        log.info("");
        log.info("[commentDelete]" + commentNo);

        int result = commentService.commentDelete(commentNo);

        return result;
    }

}
