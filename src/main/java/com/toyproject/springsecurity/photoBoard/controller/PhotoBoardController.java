package com.toyproject.springsecurity.photoBoard.controller;

import com.toyproject.springsecurity.common.comment.dto.CommentDTO;
import com.toyproject.springsecurity.common.util.Pagenation;
import com.toyproject.springsecurity.common.util.SelectCriteria;
import com.toyproject.springsecurity.photoBoard.model.dto.PhotoBoardDTO;
import com.toyproject.springsecurity.photoBoard.model.dto.PhotoBoardFileDTO;
import com.toyproject.springsecurity.photoBoard.model.service.PhotoBoardService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/photo-board")
public class PhotoBoardController {

    @Value("${file.dir}")
    private String filePath;

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final PhotoBoardService photoBoardService;
    private final Pagenation pagenation;

    @Autowired
    public PhotoBoardController(PhotoBoardService photoBoardService, Pagenation pagenation){

        this.photoBoardService = photoBoardService;
        this.pagenation = pagenation;
    }

    @ApiOperation(value = "사진 게시판 페이지 이동", notes = "사진 게시판 페이지 이동")
    @GetMapping
    public ModelAndView photoBoardMain(ModelAndView mv,HttpServletRequest request){

        String currentPage = request.getParameter("currentPage");
        int pageNo = 1;

        if(currentPage != null && !"".equals(currentPage)) {
            pageNo = Integer.parseInt(currentPage);
        }

        String searchCondition = request.getParameter("searchCondition");
        String searchValue = request.getParameter("searchValue");

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);

        int photoBoardCount = photoBoardService.photoBoardCount(searchMap);

        SelectCriteria selectCriteria = null;

        if(searchCondition != null && searchCondition != ""){
            selectCriteria = pagenation.getSelectCriteria(pageNo, photoBoardCount);
        } else {
            selectCriteria = pagenation.getSelectCriteria(pageNo, photoBoardCount, searchCondition, searchValue);
        }

        log.info("selectCriteria = {} ", selectCriteria);

        List<PhotoBoardDTO> photoBoardList = photoBoardService.photoBoardList(selectCriteria);

        String url = "/photoBoard/photoBoardList";

        mv.addObject("photoBoardList", photoBoardList);
        mv.addObject("selectCriteria", selectCriteria);
        mv.setViewName(url);

        return mv;
    }

    @GetMapping("/get/{no}")
    public ModelAndView photoBoardDetail(@PathVariable("no") int no, ModelAndView mv, Principal principal,
                                            HttpServletRequest request){
        String amount = request.getParameter("amount");
        int pageNo = 1;

        if(amount != "" && amount != null){

            pageNo = Integer.parseInt(amount);
        }

        log.info("no : {}", no);

        String loginUser = principal.getName();
        String url = "/photoBoard/photoBoardDetail";

        PhotoBoardDTO photoBoard = photoBoardService.photoBoardDetail(no);
        photoBoardService.photoBoardIncreaseCount(no);
        int commentCount = photoBoardService.photoBoardCommentCount(no);

        log.info("photoBoard : {}", photoBoard);

        SelectCriteria selectCriteria = null;
        selectCriteria = pagenation.getCommentList(pageNo, commentCount, no);
        log.info("selectCriteria : {}" , selectCriteria);

        List<CommentDTO> commentList = photoBoardService.photoBoardCommentList(selectCriteria);
        log.info("commentList : {}" , commentList);

        mv.addObject("photoBoard", photoBoard);
        mv.addObject("selectCriteria", selectCriteria);
        mv.addObject("commentList", commentList);
        mv.setViewName(url);

        return mv;
    }

    @ApiOperation(value = "사진 게시판 등록 페이지 이동", notes = "사진 게시판 등록 페이지 이동")
    @GetMapping("/write")
    public String photoBoardInsertPage() {

        String url = "/photoBoard/photoBoardInsert";

        return url;
    }

    @PostMapping("/write")
    public String photoBoardInsert(@RequestParam(value="file", required=false) MultipartFile file,
                                   RedirectAttributes rttr, @ModelAttribute PhotoBoardDTO photoBoard) {
        String changeFileName = UUID.randomUUID().toString().replace("-", "");
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        PhotoBoardFileDTO photoBoardFile = new PhotoBoardFileDTO();

        log.info("fileName : {}", file.getOriginalFilename());
        log.info("changeFileName : {}", changeFileName);
        log.info("photoBoard : {} ", photoBoard);

        if(file.getSize() > 0){

            photoBoardFile.setOrgFileName(file.getOriginalFilename());
            photoBoardFile.setChangeFileName(changeFileName + ext);
            photoBoardFile.setFileSize((int)file.getSize());
            photoBoardFile.setFilePath(filePath + "photoBoard" + "/");

            photoBoardService.photoBoardInsert(photoBoard);
            photoBoardService.photoBoardFileInsert(photoBoardFile);

            log.info("filePath : {}", filePath + "photoBoard" + "/" + changeFileName + ext);

            try {

                file.transferTo(new File(filePath + "photoBoard" + "/" + changeFileName + ext));
            } catch (IOException e) {

                e.printStackTrace();
                new File(filePath + "photoBoard" +"/" + changeFileName + ext).delete();
            }
        }

        File mkdir = new File(filePath + "photoBoard");
        if(!mkdir.exists()){

            mkdir.mkdirs();
        }

        rttr.addFlashAttribute("message", "게시판 글 등록 성공!");

        return "redirect:/photo-board";
    }
}
