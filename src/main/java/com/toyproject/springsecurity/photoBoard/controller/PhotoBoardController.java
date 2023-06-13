package com.toyproject.springsecurity.photoBoard.controller;

import com.toyproject.springsecurity.photoBoard.model.service.PhotoBoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/photo-board")
public class PhotoBoardController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final PhotoBoardService photoBoardService;

    @Autowired
    public PhotoBoardController(PhotoBoardService photoBoardService){

        this.photoBoardService = photoBoardService;
    }

    @ApiOperation(value = "사진 게시판 페이지 이동", notes = "사진 게시판 페이지 이동")
    @GetMapping
    public ModelAndView photoBoardMain(ModelAndView mv){

        String url = "/photoBoard/photoBoardList";

        mv.setViewName(url);

        return mv;
    }

    @GetMapping("/write")
    public String photoBoardInsertPage(){

        String url = "/photoBoard/photoBoardInsert";

        return url;
    }

    @PostMapping("/write")
    public String photoBoardInsert(){

        return "";
    }
}
