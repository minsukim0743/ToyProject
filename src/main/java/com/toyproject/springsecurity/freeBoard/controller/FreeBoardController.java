package com.toyproject.springsecurity.freeBoard.controller;

import com.toyproject.springsecurity.common.util.Pagenation;
import com.toyproject.springsecurity.common.util.SelectCriteria;
import com.toyproject.springsecurity.freeBoard.model.dto.FreeBoardDTO;
import com.toyproject.springsecurity.freeBoard.model.service.FreeBoardService;
import com.toyproject.springsecurity.main.model.dto.CommentDTO;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.xml.stream.events.Comment;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/free-board")
public class FreeBoardController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final FreeBoardService freeBoardService;
    private final Pagenation pagenation;

    @Autowired
    public FreeBoardController(FreeBoardService freeBoardService, Pagenation pagenation) {
        this.freeBoardService = freeBoardService;
        this.pagenation = pagenation;
    }


    @ApiOperation(value = "자유게시판 페이지 이동", notes = "자유게시판 페이지 이동 메소드")
    @GetMapping
    public ModelAndView freeBoardMain(ModelAndView mv, HttpServletRequest request){

        int pageNo = 1;

        String searchCondition = request.getParameter("searchCondition");
        String searchValue = request.getParameter("searchValue");

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);

        int freeBoardCount = freeBoardService.freeBoardCount(searchMap);

        SelectCriteria selectCriteria = null;
        if(searchCondition == "" && searchCondition == null){

            selectCriteria = pagenation.getSelectCriteria(pageNo, freeBoardCount);
        }else {
            selectCriteria = pagenation.getSelectCriteria(pageNo, freeBoardCount, searchCondition, searchValue);
        }

        List<FreeBoardDTO> freeBoardList = freeBoardService.freeBoardList(selectCriteria);

        log.info("[FreeBoardList] : {}",  freeBoardList);
        log.info("[FreeBoardCount] : {}", freeBoardCount);

        mv.addObject("freeBoardList", freeBoardList);
        mv.addObject("selectCriteria", selectCriteria);
        mv.setViewName("/freeBoard/freeBoardList");

        return mv;
    }

    @ApiOperation(value = "자유게시판 게시글 등록 페이지 이동", notes = "자유게시판 게시글 등록 페이지 이동 메소드")
    @GetMapping("/write")
    public String freeBoardInsertPage(){

        return "/freeBoard/freeBoardInsert";
    }

    @ApiOperation(value = "자유게시판 게시글 등록", notes = "유저가 자유게시판 게시글 등록하는 메소드")
    @PostMapping("/write")
    public String freeBoardInsert(@ModelAttribute FreeBoardDTO freeBoard, RedirectAttributes rttr){

        log.info("[freeBoard] : {}", freeBoard);
        freeBoardService.freeBoardInsert(freeBoard);

        rttr.addFlashAttribute("message", "게시글 작성 성공!");

        return "redirect:/free-board";
    }

    @ApiOperation(value = "자유게시판 게시글 상세페이지 페이지 이동",
            notes = "자유게시판 게시글 상세페이지로 이동하며 클릭 시 조회수 + 1 증가")
    @GetMapping("/get/{no}")
    public ModelAndView freeBoardDetail(@PathVariable("no") int no, ModelAndView mv){

        FreeBoardDTO freeBoard = freeBoardService.freeBoardDetail(no);
        freeBoardService.freeBoardDetailCount(no);
        List<CommentDTO> comments = freeBoardService.freeBoardDetailCommentList(no);

        log.info("[freeBoard] : {}", freeBoard);

        mv.addObject("freeBoard", freeBoard);
        mv.addObject("comments", comments);
        mv.setViewName("/freeBoard/freeBoardDetail");

        return mv;
    }

    @ApiOperation(value = "자유게시판 게시글 수정 페이지 이동", notes = "로그인한 계정과 동일한 작성자만 게시글 번호를 받아와" +
            " 수정페이지로 이동 가능하다" )
    @GetMapping("/put/{no}")
    public ModelAndView freeBoardUpdatePage(@PathVariable("no") int no, ModelAndView mv){

        FreeBoardDTO freeBoard = freeBoardService.freeBoardDetail(no);

        log.info("[freeBoard] : {}", freeBoard);

        mv.addObject("freeBoard", freeBoard);
        mv.setViewName("/freeBoard/freeBoardUpdate");

        return mv;
    }

    @ApiOperation(value = "자유게시판 게시글 수정 메소드", notes = "게시글 내용과 제목을 수정할 수 있으며 수정 완료 시에는" +
            " 해당 게시글 번호 상세페이지로 이동한다.")
    @PostMapping("/put")
    public String freeBoardUpdate(@RequestParam int no, @ModelAttribute FreeBoardDTO freeBoard,
                                  RedirectAttributes rttr){

        int result = freeBoardService.freeBoardUpdate(freeBoard);
        String url = null;

        log.info("[freeBoard] : {}", freeBoard);

        if(result == 1){

            url = "redirect:/free-board/get/" + no;
            rttr.addFlashAttribute("message", "게시글 수정이 완료되었습니다.");
        } else {

            url = "redirect:/free-board/put/" + no;
            rttr.addFlashAttribute("message", "게시글 수정을 실패하였습니다.");
        }
        
        return url;
    }

}
