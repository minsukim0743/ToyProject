package com.toyproject.springsecurity.photoBoard.controller;

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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/photo-board")
public class PhotoBoardController {

    @Value("${file.dir}")
    private String filePath;

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

    @ApiOperation(value = "사진 게시판 등록 페이지 이동", notes = "사진 게시판 등록 페이지 이동")
    @GetMapping("/write")
    public String photoBoardInsertPage() {

        String url = "/photoBoard/photoBoardInsert";

        return url;
    }

    @PostMapping("/write")
    public String photoBoardInsert(@RequestParam(value="file", required=false) MultipartFile file,
                                   RedirectAttributes rttr, @ModelAttribute PhotoBoardDTO PhotoBoard)
            throws IOException {

        String changeFileName = UUID.randomUUID().toString().replace("-", "");
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        PhotoBoardFileDTO photoBoardFile = new PhotoBoardFileDTO();

        log.info("fileName : {}", file.getOriginalFilename());
        log.info("changeFileName : {}", changeFileName);

        if(file.getSize() > 0){

            photoBoardFile.setOrgFileName(file.getOriginalFilename());
            photoBoardFile.setChangeFileName(changeFileName + ext);
            photoBoardFile.setFileSize((int)file.getSize());
            photoBoardFile.setFilePath(filePath + "photoBoard" + "/");

            photoBoardService.photoBoardInsert(PhotoBoard);
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
