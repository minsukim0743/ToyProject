package com.toyproject.springsecurity.login.model.service;

import com.toyproject.springsecurity.login.model.dto.CommentDTO;

import java.text.SimpleDateFormat;
import java.util.List;

public interface CommentService {

    // 댓글 리스트 조회하기
    List<CommentDTO> commentList();

    // 댓글 등록하기
    int commentInsert(CommentDTO comment);

    // 댓글 개수 조회하기
    int selectTotalCount();

    List<CommentDTO> selectCommentList();
}
