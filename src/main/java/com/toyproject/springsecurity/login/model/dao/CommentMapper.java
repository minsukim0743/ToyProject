package com.toyproject.springsecurity.login.model.dao;

import com.toyproject.springsecurity.login.model.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.text.SimpleDateFormat;
import java.util.List;

@Mapper
public interface CommentMapper {
    // 댓글 리스트 조회하기
    List<CommentDTO> commentList();

    // 댓글 등록하기
    int commentInsert(CommentDTO comment);

    // 댓글 개수 조회하기
    int selectTotalCount();

    // 댓글 삭제
    int commentDelete(int commentNo);
}
