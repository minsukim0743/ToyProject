package com.toyproject.springsecurity.login.model.service;

import com.toyproject.springsecurity.login.model.dao.CommentMapper;
import com.toyproject.springsecurity.login.model.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper) {

        this.commentMapper = commentMapper;
    }

    // 댓글 리스트 조회하기
    @Override
    public List<CommentDTO> commentList() {

        return commentMapper.commentList();
    }

    // 댓글 등록하기
    @Override
    @Transactional
    public int commentInsert(CommentDTO comment) {

        return commentMapper.commentInsert(comment);
    }

    // 댓글 개수 조회하기
    @Override
    public int selectTotalCount() {

        return commentMapper.selectTotalCount();
    }

    @Override
    public List<CommentDTO> selectCommentList() {

        return commentMapper.selectCommentList();
    }


}
