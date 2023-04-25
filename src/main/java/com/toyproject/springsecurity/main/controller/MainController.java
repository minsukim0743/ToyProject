package com.toyproject.springsecurity.main.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/main")
public class MainController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value = "메인페이지 이동", notes = "메인페이지로 이동하는 메소드")
    @GetMapping
    public ModelAndView main(ModelAndView mv) {

        // 댓글 개수 조회하기
        mv.setViewName("/main/main");

        return mv;
    }

//    @ApiOperation(value = "댓글 등록", notes = "사용자가 댓글 등록하는 Ajax메소드")
//    @PostMapping("/main/commentInsert")
//    @ResponseBody
//    public int commentInsert(@ModelAttribute CommentDTO comment) {
//
//        log.info("");
//        log.info("");
//        log.info("[commentInsert]" + comment);
//
//        int result = commentService.commentInsert(comment);
//
//        return result;
//    }
//
//    @ApiOperation(value = "댓글 조회", notes = "댓글 조회하는 Ajax 메소드")
//    @GetMapping("/main/comments")
//    @ResponseBody
//    public String commentList() {
//
//        Gson gson = new Gson();
//
//        List<CommentDTO> commentList = commentService.commentList();
//
//        log.info("");
//        log.info("");
//        log.info("[commentList]" + commentList);
//
//        return gson.toJson(commentList);
//    }
//
//    @ApiOperation(value = "댓글 삭제", notes = "댓글번호로 해당 댓글을 삭제하는 Ajax 메소드")
//    @PostMapping("/main/comment/{commentNo}")
//    @ResponseBody
//    public int commentDelete(@PathVariable int commentNo) {
//
//        log.info("");
//        log.info("");
//        log.info("[commentDelete]" + commentNo);
//
//        int result = commentService.commentDelete(commentNo);
//
//        return result;
//    }
//
//    @ApiOperation(value = "댓글 좋아요", notes = "댓글번호로 해당 댓글 좋아요 수를 + 1 하는 Ajax 메소드 / 한개의 아이디로 같은 댓글 중복 좋아요 X")
//    @PostMapping("/main/commentLike/{commentNo}")
//    @ResponseBody
//    public void commentLike(@PathVariable int commentNo, Principal principal) {
//
//        String nickName = principal.getName();
//
//        log.info("");
//        log.info("");
//        log.info("[commentDelete]" + commentNo);
//        log.info("[commentDelete]" + nickName);
//
//        // COMMENT_LIKE 테이블 추가
//        commentService.commentLike(commentNo, nickName);
//        // POST_COMMENT COMMENT_LIKE 컬럼 + 1
//        commentService.commentLike2(commentNo);
//
//    }
}
