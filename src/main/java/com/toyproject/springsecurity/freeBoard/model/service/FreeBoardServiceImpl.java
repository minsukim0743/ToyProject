package com.toyproject.springsecurity.freeBoard.model.service;

import com.toyproject.springsecurity.common.comment.dto.CommentLikeDTO;
import com.toyproject.springsecurity.common.util.SelectCriteria;
import com.toyproject.springsecurity.freeBoard.model.dao.FreeBoardMapper;
import com.toyproject.springsecurity.freeBoard.model.dto.FreeBoardDTO;
import com.toyproject.springsecurity.common.comment.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class FreeBoardServiceImpl implements FreeBoardService {

    private final FreeBoardMapper freeBoardMapper;

    @Autowired
    public FreeBoardServiceImpl(FreeBoardMapper freeBoardMapper) {
        this.freeBoardMapper = freeBoardMapper;
    }

    @Override
    public List<FreeBoardDTO> freeBoardList(SelectCriteria selectCriteria) {
        return freeBoardMapper.freeBoardList(selectCriteria);
    }

    @Override
    public int freeBoardCount(Map<String, String> searchMap) {
        return freeBoardMapper.freeBoardCount(searchMap);
    }

    @Override
    @Transactional
    public void freeBoardInsert(FreeBoardDTO freeBoard) {

        freeBoardMapper.freeBoardInsert(freeBoard);
    }

    @Override
    public FreeBoardDTO freeBoardDetail(int no) {

        return freeBoardMapper.freeBoardDetail(no);
    }

    @Override
    public void freeBoardDetailCount(int no) {

        freeBoardMapper.freeBoardDetailCount(no);
    }

    @Override
    @Transactional
    public int freeBoardUpdate(FreeBoardDTO freeBoard) {

        return freeBoardMapper.freeBoardUpdate(freeBoard);
    }

    @Override
    public List<CommentDTO> freeBoardDetailCommentList(int no) {

        return freeBoardMapper.freeBoardDetailCommentList(no);
    }

    @Override
    public int freeBoardCommentInsert(Map<String, String> paramMap) {

        return freeBoardMapper.freeBoardCommentInsert(paramMap);
    }

    @Override
    public int freeBoardCommentDelete(int commentNo) {

        return freeBoardMapper.freeBoardCommentDelete(commentNo);
    }

    @Override
    public int freeBoardCommentLike(int commentNo, int no, String loginUser) {

        return freeBoardMapper.freeBoardCommentLike(commentNo, no, loginUser);
    }

    @Override
    public int freeBoardCommentLikeCount(int commentNo, int no) {
        return freeBoardMapper.freeBoardCommentLikeCount(commentNo, no);
    }

    @Override
    public List<CommentLikeDTO> freeBoardSelectCommentLike(int no, String loginUser) {
        return freeBoardMapper.freeBoardSelectCommentLike(no, loginUser);
    }

    @Override
    public int freeBoardCommentLikeCancle(int commentNo, int no, String loginUser) {
        return freeBoardMapper.freeBoardCommentLikeCancle(commentNo, no, loginUser);
    }

    @Override
    public int freeBoardCommentLikeMinusCount(int commentNo, int no) {
        return freeBoardMapper.freeBoardCommentLikeMinusCount(commentNo, no);
    }
}
