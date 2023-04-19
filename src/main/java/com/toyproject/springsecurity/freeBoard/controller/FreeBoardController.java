package com.toyproject.springsecurity.freeBoard.controller;

import com.toyproject.springsecurity.common.util.Pagenation;
import com.toyproject.springsecurity.common.util.SelectCriteria;
import com.toyproject.springsecurity.freeBoard.model.dto.FreeBoardDTO;
import com.toyproject.springsecurity.freeBoard.model.service.FreeBoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FreeBoardController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final FreeBoardService freeBoardService;
    private final Pagenation pagenation;

    @Autowired
    public FreeBoardController(FreeBoardService freeBoardService, Pagenation pagenation) {
        this.freeBoardService = freeBoardService;
        this.pagenation = pagenation;
    }


    @GetMapping("/free-board")
    public ModelAndView freeBoardMain(ModelAndView mv){

        List<FreeBoardDTO> freeBoardList = freeBoardService.freeBoardList();
        int freeBoardCount = freeBoardService.freeBoardCount();

        int pageNo = 1;

        SelectCriteria selectCriteria = null;
        selectCriteria = pagenation.getSelectCriteria(pageNo, freeBoardCount);

        log.info("[FreeBoardList] : {}",  freeBoardList);
        log.info("[FreeBoardCount] : {}", freeBoardCount);

        mv.addObject("freeBoardList", freeBoardList);
        mv.addObject("freeBoardCount", freeBoardCount);
        mv.setViewName("/freeBoard/freeBoardList");

        return mv;
    }

    @GetMapping("/free-board/write")
    public String freeBoardWriter(){

        return "/freeBoard/freeBoardWrite";
    }
}
